package cn.haitaoss.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-01-14 19:48
 *
 */
@Configuration
public class MybatisConfig {
    public static ThreadLocal<String> ds = new ThreadLocal<>();
    private String url = "jdbc:mysql://127.0.0.1:3306/%s?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";

    @Autowired
    private DataSourceProperties properties;

    @Bean
    @Primary
    public DataSource dataSource() {
        AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return ds.get();
            }
        };
        Map<Object, Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put("d1", d1());
        objectObjectMap.put("d2", d2());
        abstractRoutingDataSource.setTargetDataSources(objectObjectMap);

        abstractRoutingDataSource.setDefaultTargetDataSource(d1());
        return abstractRoutingDataSource;
    }

    @Bean
    public DataSource d1() {
        properties.setUrl(String.format(url, "d1"));
        DataSource build = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        return build;
    }

    @Bean
    public DataSource d2() {
        properties.setUrl(String.format(url, "d2"));
        DataSource build = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        return build;
    }

}
