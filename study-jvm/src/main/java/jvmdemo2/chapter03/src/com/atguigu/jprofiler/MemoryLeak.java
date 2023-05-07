package jvmdemo2.chapter03.src.com.atguigu.jprofiler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 12:57
 */
public class MemoryLeak {

    public static void main(String[] args) {
        while (true) {
            ArrayList beanList = new ArrayList();
            for (int i = 0; i < 500; i++) {
                Bean data = new Bean();
                data.list.add(new byte[1024 * 10]);//10kb
                beanList.add(data);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Bean {
    // ArrayList list = new ArrayList();
    static ArrayList list = new ArrayList();
    int size = 10;
    String info = "hello,atguigu";
}