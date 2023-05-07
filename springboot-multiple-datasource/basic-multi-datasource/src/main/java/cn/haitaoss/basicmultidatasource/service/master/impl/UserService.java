package cn.haitaoss.basicmultidatasource.service.master.impl;

import cn.haitaoss.basicmultidatasource.entity.master.User;
import cn.haitaoss.basicmultidatasource.mapper.master.MasterUserMapper;
import cn.haitaoss.basicmultidatasource.service.master.IMasterUserService;
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
@Service("masterUserService")
public class UserService extends ServiceImpl<MasterUserMapper, User> implements IMasterUserService {
    @Autowired
    private MasterUserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("name", name));
    }
}
