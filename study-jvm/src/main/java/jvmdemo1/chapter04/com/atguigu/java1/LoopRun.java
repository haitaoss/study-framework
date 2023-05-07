package jvmdemo1.chapter04.com.atguigu.java1;

import java.lang.reflect.Method;

/**
 * @author shkstart
 * @create 11:08
 */
public class LoopRun {
    public static void main(String args[]) {
        while (true) {
            try {
                //1. 创建自定义类加载器的实例
                MyClassLoader loader = new MyClassLoader("/Users/haitao/Documents/study-summary/study-note/sync-study-data/demo_study/study-jvm/src/main/java");
                for (int i = 0; i < 5; i++) {
                    //2. 加载指定的类
                    Class clazz = loader.findClass("chapter04.com.atguigu.java1.Demo1");
                    System.out.println(clazz.getClassLoader());  //3. 创建运行时类的实例
                    Object demo = clazz.newInstance();
                    //4. 获取运行时类中指定的方法
                    Method m = clazz.getMethod("hot");
                    //5. 调用指定的方法
                    m.invoke(demo);
                    Thread.sleep(5000);
                }


            } catch (Exception e) {
                System.out.println("not find");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

}
