package cn.haitaoss.parametricdynamicdatasource.config;


import cn.haitaoss.parametricdynamicdatasource.constant.DataSourceConstants;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 11:20
 *
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
@MapperScan(basePackages = "cn.haitaoss.multidatasource.mapper")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DynamicDataSourceConfig {
    @Bean(DataSourceConstants.DS_KEY_MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }

    @Bean(DataSourceConstants.DS_KEY_SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }

    @Bean
    @Primary // 表示 DataSource 优先通过下面的方法获取
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceConstants.DS_KEY_MASTER, masterDataSource());
        dataSourceMap.put(DataSourceConstants.DS_KEY_SLAVE, slaveDataSource());

        //设置动态数据源
        return new DynamicDataSource(dataSourceMap, masterDataSource());
    }
}
