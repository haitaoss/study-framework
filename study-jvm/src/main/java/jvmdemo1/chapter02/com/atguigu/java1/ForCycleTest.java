package jvmdemo1.chapter02.com.atguigu.java1;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-04 21:28
 * 看看 for 迭代器 增强for 的字节码的区别
 */
public class ForCycleTest {
    public void test1() {
        for (int i = 0; i < 10; i++) {

        }
    }

    public void test2() {
        for (String s : Arrays.asList("")) {

        }
    }

    public void test3() {
        Iterator<String> iterator = Arrays.asList("").iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }
}
