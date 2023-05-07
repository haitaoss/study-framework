package jvmdemo2.chapter02.com.atguigu.jmap;

import java.util.ArrayList;

/**
 * @author shkstart
 * @create 17:49
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8
 * 发生内存溢出是，自动导出dump文件（可能会出现导出失败的情况，这时候就可以使用手动命令了）
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/haitao/Desktop/4.hprof
 *
 * jmap 命令导出dump文件
 *  - jmap -dump:live,format=b,file=3.hprof 77004
 *  - jmap -dump,format=b,file=3.hprof 77004
 */
public class GCTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[1024 * 100];//100KB
            // byte[] arr = new byte[1024 * 1024];//1m
            list.add(arr);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
