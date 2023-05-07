package com.atguigu.demo;

import java.util.concurrent.TimeUnit;

/*====================================================
                时间: 2022-06-15
                讲师: 刘  辉
                出品: 尚硅谷讲师团队
======================================================*/
public class Demo07ExceptionHandle {
    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        System.out.println("执行结束");
    }
}
