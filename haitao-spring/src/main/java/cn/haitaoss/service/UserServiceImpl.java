package cn.haitaoss.service;

import cn.haitaoss.spring.Autowired;
import cn.haitaoss.spring.Component;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 10:55
 *
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private OrderService orderService;

    @Override
    public void test() {
        System.out.println("orderService = " + orderService);
    }
}
