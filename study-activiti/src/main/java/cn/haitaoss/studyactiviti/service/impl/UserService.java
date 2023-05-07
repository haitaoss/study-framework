package cn.haitaoss.studyactiviti.service.impl;

import cn.haitaoss.studyactiviti.entity.User;
import cn.haitaoss.studyactiviti.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-17 14:48
 *
 */
@Service
public class UserService implements IUserService {

    @Override
    public String getAssigneeName(User user) {
        return "name is " + user.getAssignee();
    }
}
