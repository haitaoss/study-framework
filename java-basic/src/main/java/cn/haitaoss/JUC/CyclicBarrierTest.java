package cn.haitaoss.JUC;

import java.util.concurrent.CyclicBarrier;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-13 10:51
 *
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        // CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("执行了"));
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Runnable run = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println(name + "---> enter ");
                cyclicBarrier.await(); // 堵塞，知道
                System.out.println(name + "---> start ");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(run).start();
        }
        // 重复使用
//        cyclicBarrier.reset();
    }
}
