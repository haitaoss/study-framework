package cn.haitaoss.Unsafe;

import lombok.Data;
import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2023-02-11 16:42
 *
 */
public class Test {
    public static void main(String[] args) {
        Unsafe unsafe = reflectGetUnsafe();

        ChangeThread changeThread = new ChangeThread();
        new Thread(changeThread).start();

        // unsafe.loadFence(); //加入读内存屏障。

        while (true) {
            boolean flag = changeThread.isFlag();
            // unsafe.loadFence(); //加入读内存屏障
            if (flag) {
                System.out.println("detected flag changed");
                break;
            }
            // unsafe.loadFence(); //加入读内存屏障
            System.out.println("loop...");
        }
        System.out.println("main thread end");
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class ChangeThread implements Runnable {
    /**volatile**/
    boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("subThread change flag to:" + flag);
        flag = true;
    }
}
