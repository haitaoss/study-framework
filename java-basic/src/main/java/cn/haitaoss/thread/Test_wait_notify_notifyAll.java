package cn.haitaoss.thread;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-07 14:15
 *
 */
public class Test_wait_notify_notifyAll {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        int max = 3;


        new Producer(queue, max).start();
        new Consumer(queue, max).start();
        new Consumer(queue, max).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Producer extends Thread {
    private Queue<String> queue;
    private int max;

    public Producer(Queue<String> queue, int max) {
        this.queue = queue;
        this.max = max;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == max) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("add product");
                queue.add(queue.size() + "");
                queue.notifyAll();
            }

        }
    }
}

class Consumer extends Thread {
    private Queue<String> queue;
    private int max;

    public Consumer(Queue<String> queue, int max) {
        this.queue = queue;
        this.max = max;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == 0) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("consumer content is " + queue.remove());
                queue.notifyAll();
            }

        }
    }
}