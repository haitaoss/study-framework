// package cn.haitaoss.classloader;
//
// /**
//  * @author haitao.chen
//  * email haitaoss@aliyun.com
//  * date 2022-07-09 10:04
//  * 测试双亲委派机制的缺点
//  */
// public class ClassLoaderShort {
//     public static void main(String[] args) throws Exception {
//         // 获取扩展类加载器加载的目录
//         // System.out.println(System.getProperty("java.ext.dirs"));
//
//         // 将 A 和 AFactory 编译的字节码打成 java-basic.jar ，放到 扩展类加载的目录下（jre/lib/ext）
//         // /Library/Java/JavaVirtualMachines/jdk1.8.0_261.jdk/Contents/Home/jre/lib/ext/java-basic.jar
//
//         // 如果代码编译不通过可以试试 重启idea 或者 把 java-basic.jar 添加到工程的依赖中
//         System.out.println(Class.forName("cn.haitaoss.classloader.A").getClassLoader());
//
//         System.out.println(AFactory.class.getClassLoader());
//         System.out.println(AImpl.class.getClassLoader());
//
//         // AFactory.createInstance("cn.haitaoss.classloader.AImpl"); 报错
//
//         // Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());
//         AFactory.createInstance2("cn.haitaoss.classloader.AImpl"); // 成功
//     }
// }