package cn.haitaoss.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    @Autowired
    private ApplicationContext applicationContext;

    public MyListener() {
        System.out.println("");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /** 注入不了，因为这是原生的 直接反射new的对象不是通过构造器注入的*/
        // applicationContext.getBean("orderService");
        System.out.println("cn.haitaoss.springboot.config.MyListener.contextInitialized...");
    }
}