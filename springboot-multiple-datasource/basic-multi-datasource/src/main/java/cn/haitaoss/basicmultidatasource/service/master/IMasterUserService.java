package cn.haitaoss.basicmultidatasource.service.master;

import cn.haitaoss.basicmultidatasource.entity.master.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:15
 *
 */
public interface IMasterUserService extends IService<User> {
    User getUserByName(String name);
}
