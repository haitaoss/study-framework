package cn.haitaoss.springboot.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class WebMvcConfigurationSupport1 extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        System.out.println("WebMvcConfigurationSupport1---->");
        super.addInterceptors(registry);
    }
}