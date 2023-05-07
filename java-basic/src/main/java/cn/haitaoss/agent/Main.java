package cn.haitaoss.agent;

import com.sun.tools.attach.VirtualMachine;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-03-03 15:37
 *
 */
public class Main {
    public static void main(String[] args) {

    }

    public static void test_thread() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);

        System.out.println("测试拒绝策略...");
        threadPoolExecutor.execute(() -> {
            System.out.println("start...");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("end...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("结束了.....");
    }

    public void test_agent() throws Exception {
        // 通过目标Java程序的PID 建立拿到其jvm实例
        VirtualMachine virtualMachine = VirtualMachine.attach("PID");
        // 使用jvm实例动态的加载agent
        virtualMachine.loadAgentPath("/path/agent.jar", "args");
        // 断开连接
        virtualMachine.detach();
    }
}
