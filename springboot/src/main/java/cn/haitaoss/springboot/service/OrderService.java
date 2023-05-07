package cn.haitaoss.springboot.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-08-27 11:01
 */
@Component
public class OrderService implements InitializingBean {
    public static CompletableFuture task = null;

    public OrderService() {
        System.out.println("OrderService 构造器");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        task = CompletableFuture.allOf(CompletableFuture.runAsync(() -> System.out.println("非常耗时的操作1")), CompletableFuture.runAsync(() -> System.out.println("非常耗时的操作2")), CompletableFuture.runAsync(() -> System.out.println("非常耗时的操作3")));
    }

}

class CommonCalc {
    public String calcLevel(int val) {
        switch (val) {
            case 1:
                return "A";
            case 2:
                return "B";
            default:
                return "F";
        }
    }
}