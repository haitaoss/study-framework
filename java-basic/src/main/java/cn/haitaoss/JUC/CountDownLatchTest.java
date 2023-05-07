package cn.haitaoss.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-13 09:29
 *
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Runnable run = () -> {
            countDownLatch.countDown();
           /* try {
//                Thread.sleep(Math.round(1000F));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            System.out.println(Thread.currentThread().getName() + "--->");
        };

        for (int i = 0; i < 2; i++) {
            new Thread(run).start();
        }
        countDownLatch.await();
        System.out.println("结束了");
    }
}
