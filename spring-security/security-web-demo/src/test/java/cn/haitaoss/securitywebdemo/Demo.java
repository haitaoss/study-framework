package cn.haitaoss.securitywebdemo;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-21 10:41
 *
 */
public class Demo {
    @Test
    public void testCrypt() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPwd = "atguigu";
        String encode = bCryptPasswordEncoder.encode(rawPwd);
        System.out.println(encode);

        System.out.println("--------");

        boolean matches = bCryptPasswordEncoder.matches(rawPwd, encode);
        System.out.println("matches = " + matches);
    }
}
