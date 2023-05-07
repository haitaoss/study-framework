package cn.haitaoss.securitywebdemo.config;

import cn.haitaoss.securitywebdemo.service.MyUserDetailsService3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-23 14:26
 * 实现 remember me
 */
// @Configuration
public class SecurityConfig3 extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyUserDetailsService3 usersService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 赋值数据源
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动创建表,第一次执行会创建，以后要执行就要删除掉！
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 授权设置
        // 配置认证
        http
                .formLogin()
                .loginPage("/login.html") // 配置哪个 url 为登录页面
                .loginProcessingUrl("/dologin") // 设置哪个是登录的 url。
                .usernameParameter("name") // 获取用户名的参数
                .passwordParameter("pwd"); // 获取密码的参数
        // 授权
        http
                .authorizeRequests()
                .antMatchers("/login.html", "/dologin")
                .permitAll()
                .anyRequest()
                .authenticated();
        // 开启记住我功能
        http
                .rememberMe()
                .tokenValiditySeconds(120)
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(usersService);

        // 关闭csrf
        http
                .csrf()
                .disable();

    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
