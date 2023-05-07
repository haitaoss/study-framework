package cn.haitaoss.parametricdynamicdatasource.util;

import cn.haitaoss.parametricdynamicdatasource.config.DynamicDataSource;
import cn.haitaoss.parametricdynamicdatasource.context.SpringContextHolder;
import cn.haitaoss.parametricdynamicdatasource.vo.DbInfo;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 19:02
 *
 */
public class DataSourceUtil {
    /**
     * 创建新的数据源，注意：此处只针对 MySQL 数据库
     */
    public static DataSource makeNewDataSource(DbInfo dbInfo) {
        String url = "jdbc:mysql://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getDbName()
                + "?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";
        String driveClassName = StringUtils.isEmpty(dbInfo.getDriveClassName()) ? "com.mysql.cj.jdbc.Driver" : dbInfo.getDriveClassName();
        return DataSourceBuilder
                .create()
                .url(url)
                .driverClassName(driveClassName)
                .username(dbInfo.getUsername())
                .password(dbInfo.getPassword())
                .build();
    }

    /**
     * 添加数据源到动态源中
     */
    public static void addDataSourceToDynamic(String key, DataSource dataSource) {
        DynamicDataSource dynamicDataSource = SpringContextHolder
                .getContext()
                .getBean(DynamicDataSource.class);
        dynamicDataSource.addDatasource(key, dataSource);
    }

    /**
     * 根据数据库连接信息添加数据源到动态源中
     * @param key
     * @param dbInfo
     */
    public static void addDataSourceToDynamic(String key, DbInfo dbInfo) {
        DataSource dataSource = makeNewDataSource(dbInfo);
        addDataSourceToDynamic(key, dataSource);
    }
}