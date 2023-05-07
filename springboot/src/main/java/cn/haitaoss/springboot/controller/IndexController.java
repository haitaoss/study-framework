// package cn.haitaoss.springboot.controller;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Scope;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.context.annotation.RequestScope;
//
// /**
//  * @author haitao.chen
//  * email haitaoss@aliyun.com
//  * date 2022-08-14 19:51
//  */
// @RestController
// public class IndexController {
//
//     @Autowired
//     private ApplicationContext applicationContext;
//
//     @Autowired
//     private A same;
//
//     @Autowired
//     private B b;
//
//     @Autowired
//     private C c;
//
//     @GetMapping("/1")
//     public void test() {
//         System.out.println(same);
//         System.out.println("====");
//         System.out.println(applicationContext.getBean(A.class));
//         System.out.println(applicationContext.getBean(A.class));
//         System.out.println(applicationContext.getBean(A.class));
//         System.out.println("haha");
//     }
// }
//
// @Component
// @RequestScope
// class A {}
//
// @Component
// class B {}
//
// @Component
// @Scope("prototype")
// class C {}
//
