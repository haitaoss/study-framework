package cn.haitaoss.thread;


import java.lang.ref.WeakReference;
import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-07 15:36
 */
public class Test_Atomic {
    public static void main(String[] args) throws InterruptedException {
       /* AtomicInteger atomicInteger = new AtomicInteger(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> atomicInteger.getAndAdd(1)).start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(atomicInteger.get());*/


        ReentrantLock reentrantLock = new ReentrantLock();

        PriorityQueue<String> strings = new PriorityQueue<String>();
        strings.add("1");
        strings.add("2");

        System.out.println(strings.poll());
    }
}