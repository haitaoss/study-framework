package cn.haitaoss.hotswap;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-09 12:46
 *
 */
public class LoopRun {
    static List<Class> classList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        while (true) {
            MyClassLoader myClassLoader = new MyClassLoader("/Users/haitao/Documents/study-summary/study-note/sync-study-data/demo_study/study-jvm/src/main/java/");

            Class<?> clazz = myClassLoader.loadClass("cn.haitaoss.hotswap.Demo1");
            classList.add(clazz);
            System.out.println(clazz.getClassLoader());
            Method method1 = clazz.getMethod("method1");
            Object obj = clazz.newInstance();
            method1.invoke(obj);

            TimeUnit.SECONDS.sleep(1);

        }
    }
}
