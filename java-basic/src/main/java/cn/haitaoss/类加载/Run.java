package cn.haitaoss.类加载;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-20 21:01
 *
 */
public class Run {
    public static void main(String[] args) {
       /* A a = new A();
        a.test1();
        a.test2();*/


        String sb = new String("hello");

        System.out.println(sb == x(sb));

    }

    public static String x(String p) {
        return p;
    }

}
