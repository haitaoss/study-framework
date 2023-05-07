package cn.haitaoss.securitywebdemo.service;

import cn.haitaoss.securitywebdemo.entity.Users;
import cn.haitaoss.securitywebdemo.mapper.UserInfoMapper;
import cn.haitaoss.securitywebdemo.mapper.UsersMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

// @Service("userDetailsService")
public class MyUserDetailsService3 implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用usersMapper方法，根据用户名查询数据库
        QueryWrapper<Users> wrapper = new QueryWrapper();
        // where username=?
        wrapper.eq("username", username);
        Users users = usersMapper.selectOne(wrapper);
        //判断
        if (users == null) {//数据库没有用户名，认证失败
            throw new UsernameNotFoundException("用户名不存在！");
        }

        List<GrantedAuthority> auths = new ArrayList<>();
        // 用户对应的权限
        userInfoMapper
                .selectMenuByUserId(users.getId())
                .forEach(item -> auths.add(new SimpleGrantedAuthority(item.getPermission())));
        userInfoMapper
                .selectRoleByUserId(users.getId())
                .forEach(item -> auths.add(new SimpleGrantedAuthority("ROLE_" + item.getName())));

        //从查询数据库返回users对象，得到用户名和密码，返回
        return new User(users.getUsername(),
                new BCryptPasswordEncoder().encode(users.getPassword()), auths);
    }
}
