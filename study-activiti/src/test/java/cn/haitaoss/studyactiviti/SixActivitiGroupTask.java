package cn.haitaoss.studyactiviti;

import cn.haitaoss.studyactiviti.util.SecurityUtil;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-18 08:41
 * 1. 根据候选人查询组任务 taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskCandidateUser(candidateUser).list();
 * 2. 拾取组任务 taskService.claim(taskId, userId);
 * 		即使该用户不是候选人也能拾取，建议拾取时校验是否有资格。组任务拾取后，该任务已有负责人，通过候选人将查询不到该任务
 * 3. 归还组任务  taskService.setAssignee(taskId, null);
 * 		建议归还任务前校验该用户是否是该任务的负责人。也可以通过setAssignee方法将任务委托给其它用户负责，注意被委托的用户可以不是候选人（建议不要这样使用）
 * 4. 任务交接 taskService.setAssignee(taskId, candidateuser);
 * 		任务交接,任务负责人将任务交给其它候选人办理该任务
 */
@SpringBootTest
public class SixActivitiGroupTask {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SecurityUtil securityUtil;

    @Test
    public void testDeployProcess() {
        repositoryService
                .createDeployment()
                .name("测试组任务（一个任务多个负责人）")
                .addClasspathResource("bpmn/evection6.bpmn")
                .addClasspathResource("bpmn/evection6.png")
                .deploy();
    }

    @Test
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("evection6");
    }

    @Test
    public void findGroupTaskList() {
        securityUtil.logInAs("jack");
        // 流程定义key
        String processDefinitionKey = "evection6";
        // 任务候选人
        String candidateUser = "lisi";
        //查询组任务
        List<Task> list = taskService
                .createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskCandidateUser(candidateUser)//根据候选人查询
                .list();
        for (Task task : list) {
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    @Test
    public void claimTask() {
        securityUtil.logInAs("jack");
        //  获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //要拾取的任务id
        String taskId = "6302";
        //任务候选人id
        String userId = "lisi";
        //拾取任务
        // TODO 即使该用户不是候选人也能拾取(建议拾取时校验是否有资格)
        //校验该用户有没有拾取任务的资格
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(userId)//根据候选人查询
                .singleResult();
        if (task != null) {
            //拾取任务
            taskService.claim(taskId, userId);
            System.out.println("任务拾取成功");
        }
    }
}
