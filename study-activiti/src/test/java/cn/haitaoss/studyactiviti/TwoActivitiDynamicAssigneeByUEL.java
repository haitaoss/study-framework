package cn.haitaoss.studyactiviti;

import cn.haitaoss.studyactiviti.entity.User;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-17 14:28
 * UEL Unified Expression Language 通用表达式
 * 	activiti 支持两个 UEL 表达式： UEL-value 和 UEL-method。
 *   bpmn文件中使用${} 语法动态设置值，启动流程实例的时候必须传入值
 *
 *   例如：
 *   	bpmn 使用表达式：${assignee0}, ${user.assignee}, ${user.getUserId()}, ${userService.getAssigneeName(user)}
 *   	启动流程传入参数：
 *   													map.put("assignee0","张三");
 *   													map.put("user",new User()); // 要提供 getAssignee() 方法
 *   													map.put("emp",new emp());
 *   													// spring容器中还需要添加 user、userService
 *
 */
@SpringBootTest
public class TwoActivitiDynamicAssigneeByUEL {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    @Test
    public void testAssigneeUEL() {
        // 部署流程
        repositoryService
                .createDeployment()
                .name("出差审批2-测试UEL")
                .addClasspathResource("bpmn/evection2.bpmn")
                .addClasspathResource("bpmn/evection2.png")
                .deploy();
        // 删除流程部署
        // repositoryService.deleteDeployment("e6fa1d0d-8fbb-11ec-aa26-30d16bf4f117",true);

        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("assignee0", "张三");
        User user = new User();
        user.setAssignee("shuaishuai");
        paramsMap.put("user", user);

        // 启动流程实例， 添加UEL 表达式需要的参数
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Myevection2", paramsMap);
    }
}
