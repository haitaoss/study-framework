package cn.haitaoss.api;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-22 08:41
 *
 */
public class TestIsAssignableFrom {
    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }

    public static void main(String[] args) {
        System.out.println(A.class.isAssignableFrom(C.class));
        System.out.println("-----------");
        A a = new A();
        B b = new B();
        A ba = new B();
        System.out.println("1-------------");
        System.out.println(A.class.isAssignableFrom(a.getClass()));
        System.out.println(B.class.isAssignableFrom(b.getClass()));
        System.out.println(A.class.isAssignableFrom(b.getClass()));
        System.out.println(B.class.isAssignableFrom(a.getClass()));
        System.out.println(A.class.isAssignableFrom(ba.getClass()));
        System.out.println(B.class.isAssignableFrom(ba.getClass()));
        System.out.println("2-------------");
        System.out.println(a
                .getClass()
                .isAssignableFrom(A.class));
        System.out.println(b
                .getClass()
                .isAssignableFrom(B.class));
        System.out.println(a
                .getClass()
                .isAssignableFrom(B.class));
        System.out.println(b
                .getClass()
                .isAssignableFrom(A.class));
        System.out.println(ba
                .getClass()
                .isAssignableFrom(A.class));
        System.out.println(ba
                .getClass()
                .isAssignableFrom(B.class));
        System.out.println("3-------------");
        System.out.println(Object.class.isAssignableFrom(b.getClass()));
        System.out.println(Object.class.isAssignableFrom("abc".getClass()));
        System.out.println("4-------------");
        System.out.println("a"
                .getClass()
                .isAssignableFrom(Object.class));
        System.out.println("abc"
                .getClass()
                .isAssignableFrom(Object.class));
    }
}
