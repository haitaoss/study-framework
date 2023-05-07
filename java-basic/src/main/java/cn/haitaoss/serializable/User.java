package cn.haitaoss.serializable;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.*;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 10:39
 *
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6208982825834449935L;
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) throws Exception {
				/*
							序列化ID 的作用，如果使用java OutputStream 进行序列化，那么生成的二进制文件里面会记录 UID，当你通过 ObjectInputStream 反序列化时候，会比较Class
								文件中的UID 是否一致，不一致会报错。

							但是如果使用JSON 序列化的话，就不会进行校验了

				 */
        User user = new User(10, "海涛");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/Users/haitao/Desktop/user"));
        objectOutputStream.writeObject(user);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/Users/haitao/Desktop/user"));
        User o = (User) objectInputStream.readObject();
        System.out.println("o = " + o);

        System.out.println(JSONObject.toJSONString(new User(10, "海涛")));
    }
}
