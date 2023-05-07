package cn.haitaoss.JUC;

import java.util.concurrent.Semaphore;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-13 10:40
 *
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        Runnable run = () -> {
            try {
                semaphore.acquire();
                // Thread.sleep(Math.round(1000F));
                System.out.println(Thread.currentThread().getName() + "--->");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(run).start();
        }

        // 释放信号
        // semaphore.release();
    }
}
