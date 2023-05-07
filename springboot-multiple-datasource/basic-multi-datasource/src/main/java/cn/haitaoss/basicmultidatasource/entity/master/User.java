package cn.haitaoss.basicmultidatasource.entity.master;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:13
 *
 */
@Data
@TableName("tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID = 3329591434979194188L;
    @TableId
    private Integer id;
    private String name;
}
