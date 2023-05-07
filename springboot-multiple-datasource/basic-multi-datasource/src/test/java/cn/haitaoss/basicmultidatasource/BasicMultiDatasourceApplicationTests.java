package cn.haitaoss.basicmultidatasource;

import cn.haitaoss.basicmultidatasource.entity.master.User;
import cn.haitaoss.basicmultidatasource.service.master.IMasterUserService;
import cn.haitaoss.basicmultidatasource.service.slave.ISlaveUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicMultiDatasourceApplicationTests {

    @Autowired
    private ISlaveUserService slaveUserService;
    @Autowired
    private IMasterUserService masterUserService;

    @Test
    void contextLoads() {
        User lisi = masterUserService.getUserByName("lisi");
        System.out.println("lisi = " + lisi);

        cn.haitaoss.basicmultidatasource.entity.slave.User zhangsan = slaveUserService.getUserByName("zhangsan");
        System.out.println("zhangsan = " + zhangsan);

    }

}
