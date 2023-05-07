package cn.haitaoss.securitywebdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.haitaoss.securitywebdemo.mapper")
// @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityWebDemoApplication5 {

    public static void main(String[] args) {
        SpringApplication.run(SecurityWebDemoApplication5.class, args);
    }

}
