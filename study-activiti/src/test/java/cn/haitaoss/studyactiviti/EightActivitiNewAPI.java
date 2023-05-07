package cn.haitaoss.studyactiviti;

import cn.haitaoss.studyactiviti.util.SecurityUtil;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-18 14:50
 * activiti7 整合 springboot，可以自动将 编译目录/processes/*.bpmn 部署
 */
@SpringBootTest
public class EightActivitiNewAPI {
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private SecurityUtil securityUtil;

    @Test
    public void queryProcess() {
        securityUtil.logInAs("system");
        Page<ProcessInstance> processInstancePage = processRuntime.processInstances(Pageable.of(0, 100));
        System.out.println("流程实例数量：" + processInstancePage.getTotalItems());
        for (ProcessInstance processInstance : processInstancePage.getContent()) {
            System.out.println("流程实例id：" + processInstance.getId());
            System.out.println("流程实例KEY：" + processInstance.getProcessDefinitionKey());
            System.out.println("流程实例name:" + processInstance.getName());
        }
    }

    @Test
    public void testStartProcess() {
        securityUtil.logInAs("system");
        ProcessInstance pi = processRuntime.start(ProcessPayloadBuilder.
                start().
                withProcessDefinitionKey("TestAutomicDeploy").
                build());
        System.out.println("流程实例ID：" + pi.getId());
    }

    @Test
    public void testCompleteTask() {
        securityUtil.logInAs("jack");
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
        if (taskPage.getTotalItems() > 0) {
            for (Task task : taskPage.getContent()) {
                taskRuntime.claim(TaskPayloadBuilder.
                        claim().
                        withTaskId(task.getId())
                        .build());
                System.out.println("任务：" + task);
                taskRuntime.complete(TaskPayloadBuilder.
                        complete().
                        withTaskId(task.getId())
                        .build());
            }
        }
        Page<Task> taskPage2 = taskRuntime.tasks(Pageable.of(0, 10));
        if (taskPage2.getTotalItems() > 0) {
            System.out.println("任务：" + taskPage2.getContent());
        }
    }
}
