package cn.haitaoss.securitywebdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-21 17:14
 *
 */
@Controller
public class SecurityConfig4Controller {
    @GetMapping("/csrf")
    public String csrfPage() {
        return "csrf";
    }

    @PostMapping("/test/csrf")
    @ResponseBody
    public Object csrf() {

        System.out.println("hello csrf...");
        return ResponseEntity.ok();
    }
}
