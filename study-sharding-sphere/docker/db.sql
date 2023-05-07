use demo_ds_master;

-- 为了方便测试，就不搞数据库主从复制了，手动在主库和从库创建表
create table if not exists demo_ds_master.t_order(
    order_id bigint not null,
    user_id int not null,
    status varchar(50) ,
    primary key (order_id)
);
create table if not exists demo_ds_slave_0.t_order(
    order_id bigint not null,
    user_id int not null,
    status varchar(50) ,
    primary key (order_id)
);
create table if not exists demo_ds_slave_1.t_order(
    order_id bigint not null,
    user_id int not null,
    status varchar(50) ,
    primary key (order_id)
);

-- 向表插入数据，不指定数据源（会插入写库）
insert into t_order values(10,1,'init');

-- 读数据，不指定数据源（会从slave 库读取）
select * from t_order;