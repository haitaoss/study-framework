package cn.haitaoss;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-03 11:23
 *
 */
public class TestException {
    public static void main(String[] args) {
        invokeMethodA();
    }

    private static void invokeMethodA() {
        invokeMethodB();
    }

    private static void invokeMethodB() {
        invokeMethodC();
    }

    private static void invokeMethodC() {
        throw new RuntimeException("出现异常");
    }
}
