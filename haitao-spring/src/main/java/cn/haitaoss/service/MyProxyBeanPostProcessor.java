package cn.haitaoss.service;

import cn.haitaoss.spring.BeanPostProcessor;
import cn.haitaoss.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 16:06
 *
 */
@Component
public class MyProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if (beanName.equals("userServiceImpl")) {
            System.out.println("postProcessBeforeInitialization....");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if (beanName.equals("userServiceImpl")) {
            System.out.println("postProcessAfterInitialization...");

            return Proxy.newProxyInstance(bean.getClass()
                    .getClassLoader(), bean.getClass()
                    .getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("aop....");
                    return method.invoke(bean, args);
                }
            });
        }
        return bean;

    }
}
