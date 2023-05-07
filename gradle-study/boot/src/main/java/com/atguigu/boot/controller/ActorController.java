package com.atguigu.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-23 15:08
 *
 */
@RestController
@RequestMapping("actor")
public class ActorController {
    @GetMapping("/name")
    public String getActorName() {
        return "梁朝伟";
    }
}
