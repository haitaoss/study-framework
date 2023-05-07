// package cn.haitaoss.classloader;
//
// /**
//  * @author haitao.chen
//  * email haitaoss@aliyun.com
//  * date 2022-07-09 10:05
//  *
//  */
// public class AFactory {
//     public static A createInstance(String name) throws Exception {
//         return (A) Class.forName(name).newInstance();
//     }
//
//     public static A createInstance2(String name) throws Exception {
//         // 使用 这个可以解决 由于双亲委派机制，父类加载器不能加载 子加载器所加载的类的问题
//         return (A) Thread.currentThread().getContextClassLoader().loadClass(name).newInstance();
//     }
// }
