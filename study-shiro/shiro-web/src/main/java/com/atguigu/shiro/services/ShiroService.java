package com.atguigu.shiro.services;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

import java.util.Date;

public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod() {
        System.out.println("testMethod, time: " + new Date());

        Session session = SecurityUtils
                .getSubject()
                .getSession();
        Object val = session.getAttribute("key");

        System.out.println("Service SessionVal: " + val);
    }

    public void testHasRole() {
        if (!SecurityUtils
                .getSubject()
                .hasRole("admin")) {
            return;
        }
        System.out.println("testHasRole 具有权限");
    }

    public void testPermission() {
        // SecurityUtils.getSubject().isPermitted()
    }

}
 