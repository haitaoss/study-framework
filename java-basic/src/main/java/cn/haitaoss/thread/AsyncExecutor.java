package cn.haitaoss.thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 异步任务 执行器
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-05-30 09:17
 *
 */
public class AsyncExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncExecutor.class);

    /**
     * <br>异步任务提交后，都是在这个 链式 阻塞队列里 存着
     */
    private static final LinkedBlockingQueue<Runnable> TASK_WAITING_QUEUE = new LinkedBlockingQueue<>();

    /**
     * <br>定长线程池，目前大小为 10
     * <br>数量暂时写死，没有想到什么好的办法支持业务方指定
     */
    private static final ThreadPoolExecutor EXECUTOR_SERVICE = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, TASK_WAITING_QUEUE, new CusThreadFactory()      //线程工厂
            //还有个参数是拒绝策略，直接用默认的策略，抛异常就行了
    );

    private static final Thread.UncaughtExceptionHandler EXCEPTION_HANDLER = new MyThreadExceptionHandler();

    /**
     * <br>long 类型的 原子计数器，inc 操作基于 CAS
     */
    private static final AtomicLong TOTAL_COUNT = new AtomicLong();

    /**
     * 异步线程 name 前缀
     */
    private static final String AsyncThreadNamePrefix = "async-task-pool-";

    public static void run(Runnable task) {
        run(task, true);
    }

    /**
     * 提交异步任务
     * @param task
     * @param log 是否打一些 提示信息， 重要的日志信息不受这个字段影响
     * @author YellowTail
     * @since 2020-12-23
     */
    public static void run(Runnable task, boolean log) {
        EXECUTOR_SERVICE.execute(new MyRunnable(task, log));
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return EXECUTOR_SERVICE.submit(task);
    }

    /**
     * <br>得到 JVM启动之后，提交到 此执行器的任务总数
     * @return
     * @author YellowTail
     * @since 2019-04-25
     */
    public static long getTotalCount() {
        return TOTAL_COUNT.get();
    }

    /**
     * <br>得到当前 在阻塞队列里的任务数量
     * @return
     * @author YellowTail
     * @since 2019-04-25
     */
    public static int getBlockingSize() {
        return TASK_WAITING_QUEUE.size();
    }

    /**
     * <br>得到 正在运行的线程池数量
     * <br>请勿频繁调用，会影响性能
     * @return
     * @author YellowTail
     * @since 2019-10-23
     */
    public static int getActiveCount() {
        return EXECUTOR_SERVICE.getActiveCount();
    }

    /**
     * <br>得到当前线程池里线程数量总和，包含所有状态
     * <br>请勿频繁调用，会影响性能
     * @return
     * @author YellowTail
     * @since 2019-10-23
     */
    public static int getPoolSize() {
        return EXECUTOR_SERVICE.getPoolSize();
    }

    /**
     * <br>得到当前线程池的状态
     * @return
     * @author YellowTail
     * @since 2019-10-23
     */
    public static String getTerminateStatus() {
        if (EXECUTOR_SERVICE.isTerminated()) {
            return "Terminated";
        }

        if (EXECUTOR_SERVICE.isTerminating()) {
            return "Terminating";
        }

        if (EXECUTOR_SERVICE.isShutdown()) {
            return "Shutdown";
        }

        return "normal";
    }

    /**
     * <br>线程工厂
     * <br>代码基本照抄jdk，改了一点点
     *
     * @author YellowTail
     * @since 2019-09-26
     */
    static class CusThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CusThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = AsyncThreadNamePrefix + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            String name = namePrefix + threadNumber.getAndIncrement();
            Thread t = new Thread(group, r, name, 0);

            if (t.isDaemon()) t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);

            t.setUncaughtExceptionHandler(EXCEPTION_HANDLER);

            LOGGER.info("now create a async thread {}", name);

            return t;
        }
    }

    static class MyThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            LOGGER.error("AsyncUtils uncaughtException, Thread={}, e ", t, e);
            //抛了异常之后，当前线程就死掉了
        }

    }

    static class MyRunnable implements Runnable {

        static final AtomicLong ATOMIC_LONG = new AtomicLong(1);

        /**
         * 具体的任务， 是 lambda 表达式默认实现的 匿名类
         */
        private Runnable task;
        /**
         * 谁创建了此任务，直接上级
         */
        private String submitorThreadId;

        /**
         * 给每个提交的任务 加一个id
         */
        private long taskId;

        private boolean log;

        /**
         * Runnable 的 class name
         */
        private String taskName;
        private String key;

        public MyRunnable(Runnable task) {
            this(task, true);
        }

        public MyRunnable(Runnable task, boolean log) {
            this.task = task;
            this.log = log;

            submitorThreadId = Thread.currentThread().getName();

            taskId = ATOMIC_LONG.getAndIncrement();

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (int i = 0, len = stackTrace.length; i < len; i++) {
                StackTraceElement s = stackTrace[i];

                if (s.toString().contains("AsyncExecutor.run")) {
                    //下一个是各个工程的 AsyncUtils.run 方法
                    //下 下一个就是 提交异步任务的代码行

                    try {
                        taskName = stackTrace[i + 2].toString();
                    } catch (Exception e) {
                    }

                    break;
                }

                s = null;
            }

            //gc
            stackTrace = null;

            if (null == taskName || taskName.trim().isEmpty()) {
                //在没有找到的时候，
                taskName = task.getClass().getName();
            }


            //得到当前线程的请求时间戳
            this.key = System.currentTimeMillis() + "";

            if (log) {
                LOGGER.info("new MyRunnable, key {}, submitorThreadId {}, taskId {}, taskName {}",
                        key, submitorThreadId, taskId, taskName);
            }

        }

        public void run() {
            long startTimestamp = System.currentTimeMillis();

            try {
                task.run();
            } catch (Exception e) {
                //捕获一下异常，避免 任务和线程同归于尽
                LOGGER.error("occur bad things, taskId {}, ", taskId, e);
            }

            long cost = System.currentTimeMillis() - startTimestamp;

            if (log) {
                LOGGER.info("task completed, key {}, submitorThreadId {}, taskId {}, taskName {}, cost {} ms",
                        key, submitorThreadId, taskId, taskName, cost);
            }

        }


        public String getKey() {
            return key;
        }

        public String getSubmitorThreadId() {
            return submitorThreadId;
        }

        public long getTaskId() {
            return taskId;
        }

        public String getTaskName() {
            return taskName;
        }

    }
}

