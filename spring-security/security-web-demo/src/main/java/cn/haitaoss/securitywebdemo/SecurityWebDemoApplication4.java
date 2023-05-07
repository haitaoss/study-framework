package cn.haitaoss.securitywebdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan(basePackages = "cn.haitaoss.securitywebdemo.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityWebDemoApplication4 {

    public static void main(String[] args) {
        SpringApplication.run(SecurityWebDemoApplication4.class, args);
    }

}
