package cn.haitaoss.验证类加载;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-20 08:31
 *
 */
public class Loader {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("cn.haitaoss.验证类加载.Run");
        System.out.println("类加载完了");
        // TODOHAITAO: 2022/7/20 没问题，可以加载Run类，但是 类加载的链接的解析阶段 不是会将符号引用换成直接引用吗，不应该是在 解析 就会找不到方法吗
    }
}
