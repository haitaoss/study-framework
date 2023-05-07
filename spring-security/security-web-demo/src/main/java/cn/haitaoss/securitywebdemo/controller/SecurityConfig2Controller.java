package cn.haitaoss.securitywebdemo.controller;

import cn.haitaoss.securitywebdemo.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-21 17:14
 *
 */
// @Controller
public class SecurityConfig2Controller {
    // 测试注解：
    @RequestMapping("testSecured")
    @ResponseBody
    @Secured({"ROLE_normal", "ROLE_admin"})
    public String helloUser() {
        return "hello,user";
    }

    @GetMapping("findAll")
    @ResponseBody
    public String findAll() {
        return "findAll";
    }

    @RequestMapping("/preAuthorize")
    @ResponseBody
    // @PreAuthorize("hasRole('ROLE_管理员')")
    @PreAuthorize("hasAnyAuthority('menu:system')")
    public String preAuthorize() {
        System.out.println("preAuthorize");
        return "preAuthorize";
    }

    @RequestMapping("/testPostAuthorize")
    @ResponseBody
    @PostAuthorize("hasAnyAuthority('menu:system2')")
    public String postAuthorize() {
        System.out.println("test--PostAuthorize");
        return "PostAuthorize";
    }

    @RequestMapping("getAll")
    @PreAuthorize("hasRole('ROLE_管理员')")
    @PostFilter("filterObject.username == 'admin1'")
    @ResponseBody
    public List<Users> getAllUser() {
        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(1l, "admin1", "6666"));
        list.add(new Users(2l, "admin2", "888"));
        return list;
    }

    @RequestMapping("getTestPreFilter")
    @PreAuthorize("hasRole('ROLE_管理员')")
    @PreFilter(value = "filterObject.id%2==0")
    @ResponseBody
    public List<Users> getTestPreFilter(@RequestBody List<Users> list) {
        list.forEach(t -> {
            System.out.println(t.getId() + "\t" + t.getUsername());
        });
        return list;
    }


}
