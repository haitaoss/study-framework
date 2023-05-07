package cn.haitaoss.tryfinallyreturn;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 14:39
 *
 */
public class TestReturnFinally {
    public static void main(String[] args) {
        invokeMethod();
        // invokeMethod2();
    }

    private static int invokeMethod() {
        int value = 0;
        try {
            // 会先将执行结果存到 局部变量表中
            return value + 10;
            // return value += 10;
        } finally {
            // return 语句的内容已经执行了，只不过还没有返回
            System.out.println("finally---> value is : " + value);
        }

    }

    private static String invokeMethod2() {
        try {
            System.out.println("try lock!!!");
            return printMsg();
        } finally {
            System.out.println("finally block!!!");
        }
    }

    private static String printMsg() {
        System.out.println("printMsg block!!!");
        return "printMsg return!!!";
    }
}
