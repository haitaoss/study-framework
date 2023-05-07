package cn.haitaoss.securitywebdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-21 17:14
 *
 */
// @Controller
public class SecurityConfig1Controller {
    @PostMapping("/success")
    public String success() {
        return "success";
    }

    @PostMapping("/fail")
    public String fail() {
        return "fail";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("unauth")
    public String unauth() {
        return "unauth";
    }

    @GetMapping("findAll")
    @ResponseBody
    public String findAll() {
        return "findAll";
    }
}
