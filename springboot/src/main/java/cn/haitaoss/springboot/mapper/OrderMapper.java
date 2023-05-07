package cn.haitaoss.springboot.mapper;

import cn.haitaoss.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-22 21:15
 */
@Mapper
public interface OrderMapper extends BaseMapper<User> {
    @Select("SELECT * FROM tbl_user")
    List<User> findAllUser();

    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) User entity);
}
