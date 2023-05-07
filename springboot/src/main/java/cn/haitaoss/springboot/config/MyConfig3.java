// package cn.haitaoss.springboot.config;
//
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
// /**
//  * @author haitao.chen
//  * email haitaoss@aliyun.com
//  * date 2022-09-10 19:25
//  *
//  */
// // @Configuration
// // @Import(WebMvcConfigurationSupport2.class)
// @ComponentScan
// @Order(-1)
// public class MyConfig3 {}
//
// @Component
// class WebMvcConfigurationSupport2 extends WebMvcConfigurationSupport {
//
//     @Override
//     protected void addInterceptors(InterceptorRegistry registry) {
//         System.out.println("WebMvcConfigurationSupport2---->");
//         super.addInterceptors(registry);
//     }
// }