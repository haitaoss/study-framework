package cn.haitaoss.securitywebdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-21 15:53
 *
 */
// @Configuration
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置认证
        http
                .formLogin()
                .loginPage("/login.html") // 配置哪个 url 为登录页面
                .loginProcessingUrl("/dologin") // 设置哪个是登录的 url。
                // .defaultSuccessUrl("/allow-success.html") // 登录成功之后跳转到哪个 url
                .successForwardUrl("/success") // 登录成功之后跳转到哪个 url
                // .failureUrl("/allow-fail.html");// 登录失败之后跳转到哪个 url
                .failureForwardUrl("/fail")// 登录失败之后跳转到哪个 url
                .usernameParameter("name") // 获取用户名的参数
                .passwordParameter("pwd"); // 获取密码的参数

        // 登出
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll();

        // 配置资源权限
        http
                .authorizeRequests()
                .antMatchers("/index", "/allow*", "/login.html", "*.css", "*.js") //表示配置请求路径
                .permitAll() // 指定 URL 无需保护。
                .antMatchers("/findAll")
                .hasAuthority("admins") // 需要用户带有admins 权限
                .antMatchers("/find")
                .hasAnyAuthority("role") // 需要主体带有 role 权限
                .antMatchers("/test/role")
                .hasRole("sale")
                .antMatchers("/test/roles")
                .hasAnyRole("xx", "menu:system")
                .anyRequest() // 其他请求
                .authenticated(); //需要认证
        // 配置 403 页面
        http
                .exceptionHandling()
                .accessDeniedPage("/unauth");
        // 关闭 csrf
        http
                .csrf()
                .disable();

    }

    @Bean
    public PasswordEncoder BCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
