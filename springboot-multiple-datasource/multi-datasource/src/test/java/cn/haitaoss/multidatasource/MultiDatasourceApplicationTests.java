package cn.haitaoss.multidatasource;

import cn.haitaoss.multidatasource.entity.User;
import cn.haitaoss.multidatasource.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultiDatasourceApplicationTests {
    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {
        User slaveUser = userService.getUserByName("slave");
        System.out.println("slaveUser = " + slaveUser);


        User master = userService.getUserByName("master");
        System.out.println("master = " + master);

        userService
                .list()
                .stream()
                .forEach(System.out::println);
    }

}
