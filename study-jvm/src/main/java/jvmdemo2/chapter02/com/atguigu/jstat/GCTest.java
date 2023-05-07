package jvmdemo2.chapter02.com.atguigu.jstat;

import java.util.ArrayList;

/**
 * @author shkstart
 * @create 17:49
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8
 *
 * 默认新生代和老年代的比例是 1:2
 *  所以新生代 60m * (1/3) = 20m
 *  然后 -XX:SurvivorRatio=8 表示，eden区和s0,s1 的比例是 8:1:1
 *  所以 s0 = s1 = 20m * (1/10) = 2m
 */
public class GCTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[1024 * 1024];//1M
            // byte[] arr = new byte[1024 * 10];//10kb
            list.add(arr);
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
