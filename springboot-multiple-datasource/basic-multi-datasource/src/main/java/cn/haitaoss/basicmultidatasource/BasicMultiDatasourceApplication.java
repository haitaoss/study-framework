package cn.haitaoss.basicmultidatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.haitaoss.basicmultidatasource.mapper")
public class BasicMultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicMultiDatasourceApplication.class, args);
    }

}
