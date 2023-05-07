# 序列化的知识点

> 文章 https://www.jianshu.com/p/321603426a81

- demo cn.haitaoss.serializable.User
- 说明：
    - 序列化ID 的作用，如果使用java OutputStream 进行序列化，那么生成的二进制文件里面会记录 UID，当你通过 ObjectInputStream 反序列化时候，会比较Class 文件中的UID
      是否一致，不一致会报错。
    - 但是如果使用JSON 序列化的话，就不会进行校验了

# finally 和 return 执行顺序

> https://www.cnblogs.com/lanxuezaipiao/p/3440471.html

- demo cn.haitaoss.tryfinallyreturn.TestReturnFinally
- 总结：
    - 如果return 得是方法的调用或者运算操作，那么是会调用方法或者执行运算的，但是这个结果不会立即返回，而是finally 代码块执行完之后再返回