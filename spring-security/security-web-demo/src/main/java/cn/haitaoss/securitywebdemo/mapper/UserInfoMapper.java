package cn.haitaoss.securitywebdemo.mapper;

import cn.haitaoss.securitywebdemo.entity.Menu;
import cn.haitaoss.securitywebdemo.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-22 12:21
 *
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 根据用户 Id 查询用户角色
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(Long userId);

    /**
     * 根据用户 Id 查询菜单
     * @param userId
     * @return
     */
    List<Menu> selectMenuByUserId(Long userId);
}
