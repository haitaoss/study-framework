package cn.haitaoss.service;

import cn.haitaoss.spring.ApplicationContext;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 15:17
 *
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);


        /*System.out.println(applicationContext.getBean("singletonBean"));
        System.out.println(applicationContext.getBean("singletonBean"));
        System.out.println(applicationContext.getBean("singletonBean"));
        System.out.println(applicationContext.getBean("prototypeBean"));
        System.out.println(applicationContext.getBean("prototypeBean"));
        System.out.println(applicationContext.getBean("prototypeBean"));*/
        // applicationContext.getBean("userService");


        /*BeanNameAwareTestBean beanNameAwareTestBean = (BeanNameAwareTestBean) applicationContext.getBean("beanNameAwareTestBean");
        beanNameAwareTestBean.sayBeanName();


        Object initializingBeanTest = applicationContext.getBean("initializingBeanTest");*/

        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.test();

    }
}
