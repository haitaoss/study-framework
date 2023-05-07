package jvmdemo1.chapter01.com.atguigu.java1;

/**
 * @author shkstart
 * @create 2020-09-06 21:07
 */
public class JavapTest {
    public static final int COUNTS = 1;

    static {
        String url = "www.atguigu.com";
    }

    public String info;
    protected char gender;
    boolean flag;
    private int num;

    {
        info = "java";
    }

    public JavapTest() {

    }

    private JavapTest(boolean flag) {
        this.flag = flag;
    }

    private void methodPrivate() {

    }

    int getNum(int i) {
        return num + i;
    }

    protected char showGender() {
        return gender;
    }

    public void showInfo() {
        int i = 10;
        System.out.println(info + i);
    }
}
