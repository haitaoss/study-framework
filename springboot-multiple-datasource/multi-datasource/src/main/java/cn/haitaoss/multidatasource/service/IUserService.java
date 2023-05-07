package cn.haitaoss.multidatasource.service;

import cn.haitaoss.multidatasource.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:15
 *
 */
public interface IUserService extends IService<User> {
    User getUserByName(String name);
}
