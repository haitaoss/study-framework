package cn.haitaoss.JUC;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-09 15:25
 *
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * {@link ReentrantLock#lock()}
         * {@link AbstractQueuedSynchronizer#acquire(int)}
         *      {@link ReentrantLock.FairSync#tryAcquire(int)}
         *      {@link ReentrantLock.NonfairSync#tryAcquire(int)}
         *      {@link AbstractQueuedSynchronizer#acquireQueued(AbstractQueuedSynchronizer.Node, int)}
         *      {@link AbstractQueuedSynchronizer#addWaiter(AbstractQueuedSynchronizer.Node)}
         * */
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
            System.out.println("====");
            condition.await();
            condition.signal();
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        CountDownLatch countDownLatch = new CountDownLatch(5);
        countDownLatch.countDown();

        countDownLatch.await();

        Thread t1 = new Thread(() -> {
            LockSupport.park();
            LockSupport.park();
            System.out.println("哈哈哈");
        });

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("解开");
        LockSupport.unpark(t1);
        LockSupport.unpark(t1);

       /* // 这个有啥好用？？
        LockSupport.park(new Object());
        LockSupport.park();

        LockSupport.unpark()*/;
    }
}
