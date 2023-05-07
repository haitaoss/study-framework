package cn.haitaoss.basicmultidatasource.service.slave.impl;

import cn.haitaoss.basicmultidatasource.mapper.slave.SlaveUserMapper;
import cn.haitaoss.basicmultidatasource.entity.slave.User;
import cn.haitaoss.basicmultidatasource.service.slave.ISlaveUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:15
 *
 */
@Service("slaveUserService")
public class UserService extends ServiceImpl<SlaveUserMapper, User> implements ISlaveUserService {
    @Autowired
    private SlaveUserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("name", name));
    }
}
