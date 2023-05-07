package cn.haitaoss.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-09 10:42
 *
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        InheritableThreadLocal<String> parent = new InheritableThreadLocal<>();
        parent.set("parent value");
        System.out.println("父线程设置的值是：" + parent.get());

        new Thread(() -> {
            System.out.println("子线程获取父线程的内容：" + parent.get());
            parent.set("son value");
            System.out.println("子线程修改父线程的内容：" + parent.get());
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("父线程设置的值是：" + parent.get());
    }
}
