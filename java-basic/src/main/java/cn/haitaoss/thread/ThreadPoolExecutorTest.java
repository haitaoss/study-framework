package cn.haitaoss.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-09 14:19
 *
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                }, new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.execute(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("存活的线程数量--->" + threadPoolExecutor.getPoolSize());
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadPoolExecutor.execute(() -> System.out.println("hello"));
        threadPoolExecutor.execute(() -> System.out.println("hello"));
        threadPoolExecutor.execute(() -> System.out.println("hello"));
        threadPoolExecutor.execute(() -> System.out.println("hello"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
