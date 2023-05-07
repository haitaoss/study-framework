package cn.haitaoss.memory;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-22 17:24
 *
 */
public class MemorySizeTest {
    int age;
    Long xx;
    String xx2;
    char[] xxx;
    short xxxx;
    double xxxxx;
    char xxxxxx;
    char[] chars = new char[1024]; // 1024 * 2

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println(Thread.currentThread());
            System.out.println("hhh");
        }).start();

        Thread thread = Thread.currentThread();
        thread.join();
    }
}
