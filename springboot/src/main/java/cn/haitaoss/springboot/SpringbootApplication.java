package cn.haitaoss.springboot;

import cn.haitaoss.springboot.config.EarlyCreate;
import cn.haitaoss.springboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages = "cn.haitaoss.springboot.mapper")
// @DependsOn({"orderService"})
@Slf4j
// @PropertySource("classpath:db.properties")
@ImportResource("classpath:spring-context.xml")
@DependsOn("userService")
@EarlyCreate("orderService")
public class SpringbootApplication extends SpringBootServletInitializer implements CommandLineRunner/*, BeanPostProcessor */ {
    /*@Autowired
    private UserService userService;*/
    @Autowired
    private User user;
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringbootApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("springboot项目启动了");
        System.out.println("user = " + user);
        // userService.test();
        // userService.test_transaction();
        // userService.test_dynamic_ds();
        // userService.test_transaction2();
    }
}
