package cn.haitaoss.helloswagger.controller;

import cn.haitaoss.helloswagger.vo.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-28 10:14
 *
 */
@RestController
@Api(value = "测试认证配置，是否排除 login开头的请求")
public class LoginController {
    @PostMapping("/login")
    public ResponseResult<String> login() {
        System.out.println("登录请求....");
        return ResponseResult.ok("登录成功");
    }
}
