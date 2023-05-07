package cn.haitaoss.quartzdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.haitaoss.quartzdemo.dao")
@SpringBootApplication()
public class QuartzDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzDemoApplication.class, args);
    }
}
