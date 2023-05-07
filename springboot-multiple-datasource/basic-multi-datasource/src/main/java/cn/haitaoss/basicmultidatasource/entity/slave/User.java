package cn.haitaoss.basicmultidatasource.entity.slave;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:13
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_user")
public class User implements Serializable {

    // private static final long serialVersionUID = -6208982825834449934L;
    private static final long serialVersionUID = -6208982825834449935L;
    @TableId
    private Integer id;
    private String name;

    public static void main(String[] args) throws Exception {
        // serialVersionUID 的作用:
        User user = new User(10, "海涛");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/Users/haitao/Desktop/user"));
        objectOutputStream.writeObject(user);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/Users/haitao/Desktop/user"));
        User o = (User) objectInputStream.readObject();
        System.out.println("o = " + o);

        System.out.println(JSONObject.toJSONString(new User(10, "海涛")));
    }
}
