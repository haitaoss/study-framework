// package cn.haitaoss.springboot.config;
//
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
// import org.springframework.boot.web.servlet.ServletRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import javax.servlet.*;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
//
// /**
//  * @author haitao.chen
//  * email haitaoss@aliyun.com
//  * date 2022-09-11 19:24
//  *
//  */
// @Configuration
// public class WebConfig1 {
//     public WebConfig1() {
//         System.out.println("WebConfig1--->");
//     }
//
//     /**
//      * 注册 servlet
//      * @return
//      */
//     @Bean
//     public ServletRegistrationBean servletRegistrationBean() {
//         MyServlet myServlet = new MyServlet();
//         return new ServletRegistrationBean(myServlet, "/");
//     }
//
//     @Bean
//     public ServletRegistrationBean servletRegistrationBean2() {
//         MyServlet2 myServlet = new MyServlet2();
//         ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(myServlet, "/");
//         servletRegistrationBean.setLoadOnStartup(1);
//         return servletRegistrationBean;
//     }
//
//     /**
//      * 注册过滤器
//      * @return
//      */
//     @Bean
//     public FilterRegistrationBean filterRegistrationBean() {
//         FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyFilter());
//         //注册该过滤器需要过滤的 url
//         // filterRegistrationBean.setUrlPatterns(Arrays.asList("/index"));
//         return filterRegistrationBean;
//     }
//
//     @Bean
//     public FilterRegistrationBean filterRegistrationBean2() {
//         FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyFilter2());
//         //注册该过滤器需要过滤的 url
//         // filterRegistrationBean.setUrlPatterns(Arrays.asList("/index"));
//         return filterRegistrationBean;
//     }
//
//     /**
//      * 注册监听器
//      * @return
//      */
//     @Bean
//     public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//         return new ServletListenerRegistrationBean(new MyListener());
//     }
// }
//
// class MyListener implements ServletContextListener {
//     public MyListener() {
//         System.out.println("");
//     }
//
//
//     @Override
//     public void contextInitialized(ServletContextEvent sce) {
//         System.out.println("MyListener 监听到 ServletContext 初始化");
//     }
//
//     @Override
//     public void contextDestroyed(ServletContextEvent sce) {
//         System.out.println("MyListener 监听到 ServletContext 销毁");
//     }
// }
//
// class MyFilter implements Filter {
//     public MyFilter() {
//         System.out.println("");
//     }
//
//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {
//         System.out.println("cn.haitaoss.springboot.config.MyFilter.init...");
//     }
//
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         System.out.println("cn.haitaoss.springboot.config.MyFilter.doFilter...");
//         chain.doFilter(request, response);
//         System.out.println("cn.haitaoss.springboot.config.MyFilter.doFilter...after");
//     }
// }
//
// class MyFilter2 implements Filter {
//     public MyFilter2() {
//         System.out.println("");
//     }
//
//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {
//         System.out.println("cn.haitaoss.springboot.config.MyFilter.init...");
//     }
//
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         System.out.println("cn.haitaoss.springboot.config.MyFilter.doFilter...");
//         chain.doFilter(request, response);
//         System.out.println("cn.haitaoss.springboot.config.MyFilter.doFilter...after");
//     }
// }
//
// class MyServlet extends HttpServlet {
//     @Override
//     public void init() throws ServletException {
//         super.init();
//     }
//
//     public MyServlet() {
//         System.out.println("");
//     }
//
//     @Override
//     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         System.out.println("cn.haitaoss.springboot.config.MyServlet.service...");
//     }
// }
//
// class MyServlet2 extends HttpServlet {
//     @Override
//     public void init() throws ServletException {
//         super.init();
//     }
//
//     public MyServlet2() {
//         System.out.println("");
//     }
//
//     @Override
//     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         System.out.println("cn.haitaoss.springboot.config.MyServlet.service...");
//     }
// }
