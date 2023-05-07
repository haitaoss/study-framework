package cn.haitaoss.springboot.controller;

import cn.haitaoss.springboot.entity.User;
import cn.haitaoss.springboot.mapper.UserMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-22 21:00
 */
@Controller
public class HelloController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    A a;

    public HelloController() {
        System.out.println("HelloController 构造器");
    }

    @Autowired
    UserMapper userMapper;

    // # localhost:8080/index
    @ResponseBody
    @GetMapping("/index")
    public Object index() {
        System.out.println("hello world!!!");
        List<User> allUser = userMapper.findAllUser();
        for (User user : allUser) {
            System.out.println("user = " + user);
        }
        return JSONObject.toJSONString(allUser);
    }

    @GetMapping("/hello")
    public String hello(ModelAndView modelAndView) {
        System.out.println("-----");
        return "/hello";
    }


    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test() {
        System.out.println("hello world!!!");
        List<User> allUser = userMapper.findAllUser();
        for (User user : allUser) {
            System.out.println("user = " + user);
        }
        return JSONObject.toJSONString(allUser);
    }

    @GetMapping("xxx")
    public void x(@RequestParam("a") A a) {
        System.out.println("hh");
    }
}

@Component
class A {
    String name;
}
