package cn.haitaoss.parametricdynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.haitaoss.parametricdynamicdatasource.mapper")
public class ParametricDynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParametricDynamicDatasourceApplication.class, args);
    }

}
