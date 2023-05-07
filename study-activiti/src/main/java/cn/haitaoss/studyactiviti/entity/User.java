package cn.haitaoss.studyactiviti.entity;

import org.springframework.stereotype.Component;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-17 14:46
 *
 */
@Component
public class User {
    private String assignee;

    public User() {
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
