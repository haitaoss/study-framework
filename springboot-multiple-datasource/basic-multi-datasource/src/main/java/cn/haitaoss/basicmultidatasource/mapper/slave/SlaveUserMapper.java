package cn.haitaoss.basicmultidatasource.mapper.slave;

import cn.haitaoss.basicmultidatasource.entity.slave.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:16
 *
 */
@Mapper
public interface SlaveUserMapper extends BaseMapper<User> {
}
