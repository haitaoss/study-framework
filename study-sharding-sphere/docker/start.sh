echo "mysql 变量"
m1="mysql-m1"
s1="mysql-s1"
echo "初始化目录"
for v in  $m1 $s1 ;  do
  if [ ! -d $v ]; then
    mkdir -p /opt/data/$v/conf.d
  fi;
done
echo "初始化m1的mysql配置文件"
cat <<EOF > /opt/data/$m1/conf.d/docker.cnf
[mysqld]
skip-host-cache
skip-name-resolve
character-set-client-handshake=FALSE
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
init-connect='SET NAMES utf8mb4'
max_connections = 1000
# 给数据库服务的唯一标识，一般为大家设置服务器Ip的末尾号
server-id=10
log-bin=master-bin
log-bin-index=master-bin.index
#设置logbin格式 基于sql语句的复制执行
binlog_format=STATEMENT
# 在作为从数据库的时候，有写入操作也要更新二进制日志文件
log-slave-updates
#表示自增长字段每次递增的量，指自增字段的起始值，其默认值是1，取值范围是1 .. 65535
auto-increment-increment=2
# 表示自增长字段从哪个数开始，指字段一次递增多少，他的取值范围是1 .. 65535
auto-increment-offset=1
EOF
echo "初始化s1的mysql配置文件"
cat <<EOF > /opt/data/$s1/conf.d/docker.cnf
[mysqld]
skip-host-cache
skip-name-resolve
max_connections = 1000
character-set-client-handshake=FALSE
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
init-connect='SET NAMES utf8mb4'
#log-error  = /var/log/mysql/error.log
server-id=11
log-bin=slave-bin.log
sync_binlog=1
#启用中继日志
relay-log=mysql-relay
EOF

echo "拷贝sharding-proxy 的配置文件"
docker run -d --name temp apache/sharding-proxy:4.1.1 && \
mkdir -p /opt/data/sharding-proxy/ && \
docker cp temp:/opt/sharding-proxy/conf /opt/data/sharding-proxy/ && \
docker rm -f temp
echo "sharding-proxy 的连接方式 mysql -u${your_user_name} -p${your_password} -h${your_host} -P13308"

echo "启动容器"
docker-compose up -d

docker cp ./mysql-connector-java-8.0.11.jar sharding-proxy:/opt/sharding-proxy/lib

echo "1. 需要手动在 master 机器上创建一个具有主从功能的用户"
echo "

[root@centos7-docker-v2 mysql-compose]# docker exec -it mysql-compose_master_1 bash
root@4b27f073eac8:/# mysql -uroot -proot
mysql> CREATE USER 'repl'@'%' IDENTIFIED BY 'repl';
mysql> GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
mysql> show master status;
#查看binlog状态 记录File 和 Position 状态稍后从库配置的时候会用
+------------------+----------+--------------+--------------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB         | Executed_Gtid_Set |
+------------------+----------+--------------+--------------------------+-------------------+
| mysql-bin.000001 |      156 |              | mysql,information_schema |                   |
+------------------+----------+--------------+--------------------------+-------------------+

"

echo "2. 需要手动在 slave 机器上设置 master的binlog 信息，然后启动slave服务"
echo "
[root@centos7-docker-v2 mysql-compose]# docker exec -it mysql-compose_slave_1 bash
root@4b27f073eac8:/# mysql -uroot -proot
mysql> change master to master_host='master',master_port=3306,master_user='repl',master_password='repl',master_log_file='mysql-bin.000001',master_log_pos=156;
mysql> start slave;
mysql> show slave status\G;
"