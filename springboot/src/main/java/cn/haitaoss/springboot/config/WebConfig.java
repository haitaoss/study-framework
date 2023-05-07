package cn.haitaoss.springboot.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-09-11 17:57
 *
 */
@ServletComponentScan
@Configuration
public class WebConfig {
    /**
     * @WebListener 注册的是
     *
     * ServletListenerRegistrationBean 注册的是
     *  addApplicationEventListener
     *  addApplicationLifecycleListener
     * */
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(new MyServletContextListener());
        return listenerRegistrationBean;
    }
}

class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }
}

@WebServlet(urlPatterns = "/haitao",name = "MyServlet")
class MyServlet extends HttpServlet {
    public MyServlet() {
        System.out.println("MyServlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("cn.haitaoss.springboot.config.MyServlet.service...");
    }
}



