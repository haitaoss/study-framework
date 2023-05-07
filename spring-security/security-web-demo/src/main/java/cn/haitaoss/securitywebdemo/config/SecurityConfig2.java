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
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置认证
        http
                .formLogin()
                .loginPage("/login.html") // 配置哪个 url 为登录页面
                .loginProcessingUrl("/dologin") // 设置哪个是登录的 url。
                .usernameParameter("name") // 获取用户名的参数
                .passwordParameter("pwd"); // 获取密码的参数
        // 配置资源权限
        http
                .authorizeRequests()
                .antMatchers("/login.html", "*.css", "*.js") //表示配置请求路径
                .permitAll() // 指定 URL 无需保护。
                .anyRequest() // 其他请求
                .authenticated(); //需要认证

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
