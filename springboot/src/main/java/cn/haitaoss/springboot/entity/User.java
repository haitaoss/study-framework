package cn.haitaoss.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-22 21:16
 *
 */
@TableName("tbl_user")
@Data
public class User {
    public User() {
        System.out.println("User 构造器");
    }

    @TableId
    Integer id;
    @TableField
    String username;

    @TableField(value = "phone")
    String xxphone;

    @Value("${name}")
    private String name2;
    @Value("${name2}")
    private String name3;

    public String getXxphone() {
        return xxphone;
    }

    public void setXxphone(String xxphone) {
        this.xxphone = xxphone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
