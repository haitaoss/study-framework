package cn.haitaoss.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter
public class MyFilter implements Filter {
    public MyFilter() {
        System.out.println("");
    }

    @Autowired
    public MyFilter(ApplicationContext applicationContext) {
        System.out.println(applicationContext);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("cn.haitaoss.springboot.config.MyFilter.init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("cn.haitaoss.springboot.config.MyFilter.doFilter...");
        chain.doFilter(request, response);
        System.out.println("cn.haitaoss.springboot.config.MyFilter.doFilter...after");
    }
}