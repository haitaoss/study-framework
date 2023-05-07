package cn.haitaoss.util;

/**
 * 当前类（子系统中定义的类）继承 ThreadPoolManager 类，设置相关参数
 */
public class ViThreadPoolManager extends ThreadPoolManager {
    private static ThreadPoolManager threadPool = null;

    public static void main(String[] args) {
        ViThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world ---> " + Thread.currentThread().getName());
            }
        });
    }

    public synchronized static ThreadPoolManager getInstance() {
        if (threadPool == null) {
            threadPool = new ViThreadPoolManager();
        }
        return threadPool;
    }

    @Override
    protected String getThreadPoolName() {
        return "com.tool.me.vi";
    }

    @Override
    protected int corePoolSize() {
        /**
         * 代码 设置返回值
         */
        return 10;
    }

    @Override
    protected int maximumPoolSize() {
        /**
         * 代码 设置返回值
         */
        return 20;
    }
}
