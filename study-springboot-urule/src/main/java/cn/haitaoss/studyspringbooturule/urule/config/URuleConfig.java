package cn.haitaoss.studyspringbooturule.urule.config;

import com.bstek.urule.KnowledgePackageReceiverServlet;
import com.bstek.urule.console.URuleServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * URULE配置
 */
@Configuration
public class URuleConfig {
    @Bean
    public ServletRegistrationBean registerUruleServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new URuleServlet(), "/urule/*");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean registerKnowledgeServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new KnowledgePackageReceiverServlet(), "/knowledgepackagereceiver");
        return servletRegistrationBean;
    }
}