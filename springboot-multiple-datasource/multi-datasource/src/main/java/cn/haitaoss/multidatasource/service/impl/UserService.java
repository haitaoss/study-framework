package cn.haitaoss.multidatasource.service.impl;


import cn.haitaoss.multidatasource.entity.User;
import cn.haitaoss.multidatasource.mapper.UserMapper;
import cn.haitaoss.multidatasource.service.IUserService;
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
@Service
// @DS(DataSourceConstants.DS_KEY_SLAVE)
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    // @DS(DataSourceConstants.DS_KEY_MASTER)
    public User getUserByName(String name) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("name", name));
    }
}
