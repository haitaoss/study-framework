package cn.haitaoss.springboot.profile;

import cn.haitaoss.springboot.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-08-16 21:47
 */
// @Configuration
public class TestConfig {
    public TestConfig() {
        System.out.println("===");
    }

    @Bean
    @Profile("prod")
    public User user() {
        return new User();
    }

    @Bean
    @Profile("dev")
    public User user2() {
        return new User();
    }
}
