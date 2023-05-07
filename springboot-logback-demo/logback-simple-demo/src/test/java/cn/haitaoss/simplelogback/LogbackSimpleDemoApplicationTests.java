package cn.haitaoss.simplelogback;

import cn.haitaoss.simplelogback.model.User;
import cn.haitaoss.simplelogback.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LogbackSimpleDemoApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        List<User> users = userService.getUsers();
        System.out.println("users = " + users);
    }

}
