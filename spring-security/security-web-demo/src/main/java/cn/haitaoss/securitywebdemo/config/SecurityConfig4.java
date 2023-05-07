package cn.haitaoss.securitywebdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-23 14:26
 * 实现 remember me
 */
// @Configuration
public class SecurityConfig4 extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
			/*	// 关闭csrf
				http
												.csrf()
												.disable();*/

    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
