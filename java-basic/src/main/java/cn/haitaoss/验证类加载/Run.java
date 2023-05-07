package cn.haitaoss.验证类加载;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-20 08:19
 */
public class Run {
    public static void main(String[] args) throws ClassNotFoundException {
        A a = new AImpl();
        a.test();
    }
}
