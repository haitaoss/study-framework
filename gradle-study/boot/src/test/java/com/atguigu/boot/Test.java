package com.atguigu.boot;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-23 15:11
 *
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostName());
    }
}
