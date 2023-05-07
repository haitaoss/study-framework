package cn.haitaoss.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-01-19 16:22
 *
 */
@WebFilter(filterName = "ResponseFilter", urlPatterns = "/*")
public class ResponseFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("ResponseFilter init...");
    }

    public void destroy() {
        System.out.println("ResponseFilter destroy...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("设置响应头信息");
        response.setContentType("application/json;charset=utf-8");
        chain.doFilter(request, response);
    }
}
