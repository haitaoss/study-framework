package cn.haitaoss.basicmultidatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:48
 *
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DatasourceConfig {

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }
}
