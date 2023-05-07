package cn.haitaoss.studyactiviti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-17 10:49
 *
 */
@SpringBootTest
public class OneActivitiApi {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ManagementService managementService;

    @Test
    public void testDeployeeProcess() {
        Deployment deploy = repositoryService
                .createDeployment()
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .name("出差审批")
                .deploy();

        System.out.println("部署name = " + deploy.getName());
        System.out.println("部署流程ID = " + deploy.getId());
    }

    @Test
    public void testStartProcess() {
        // testDeployeeProcess();
				/*
					部署name = 出差审批
部署流程ID = 0e5970f0-8f9e-11ec-a66e-30d16bf4f117
				 */
        for (int i = 0; i < 2; i++) {
            ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("myEvection", "businessKey-" + i);
            System.out.println("流程定义ID = " + myEvection.getProcessDefinitionId());
            System.out.println("流程定义key = " + myEvection.getProcessDefinitionKey());
            System.out.println("流程实例Id = " + myEvection.getProcessInstanceId());
            System.out.println("流程部署id = " + myEvection.getDeploymentId());
        }
    }

    @Test
    public void testQueryProcessInstance() {
        String processDefinitionKey = "myEvection";
        List<ProcessInstance> list = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .list();
        for (ProcessInstance processInstance : list) {
            System.out.println("流程实例ID " + processInstance.getProcessInstanceId());
            System.out.println("当前流程所属流程定义ID " + processInstance.getProcessDefinitionId());
            System.out.println("流程是否完成 " + processInstance.isEnded());
            System.out.println("流程是否暂停 " + processInstance.isSuspended());
            System.out.println("流程当前活动ID " + processInstance.getActivityId());
        }
    }

    @Test
    public void testCompleteProcess() {
        for (Task task : taskService
                .createTaskQuery()
                .taskAssignee("zhangsan")
                .processDefinitionKey("myEvection")
                .list()) {
            taskService.complete(task.getId());
            break;
        }
    }

    @Test
    public void testTaskQuery() {
        List<Task> list = taskService
                .createTaskQuery()
                .taskAssignee("zhangsan")
                .processDefinitionKey("myEvection")
                .list();

        for (Task task : list) {
            System.out.println("任务执行人 " + task.getAssignee());
            System.out.println("流程实例ID " + task.getProcessInstanceId());
            System.out.println("流程定义ID " + task.getProcessDefinitionId());
            System.out.println("taskID " + task.getId());
        }
        System.out.println("-------------------");
        // 根据业务号查询流程实例
        Task business1 = taskService
                .createTaskQuery()
                .processInstanceBusinessKey("businessKey-0")
                .singleResult();

        System.out.println("任务执行人 " + business1.getAssignee());
        System.out.println("流程实例ID " + business1.getProcessInstanceId());
        System.out.println("流程定义ID " + business1.getProcessDefinitionId());
        System.out.println("taskID " + business1.getId());

    }

    @Test
    public void testSuspendAllProcess() {
        for (ProcessDefinition myEvection : repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .list()) {
            boolean suspended = myEvection.isSuspended();
            if (suspended) {
                // 如果流程定义以及暂停 我们就启动它
                repositoryService.activateProcessDefinitionById(myEvection.getId(), true, null);
                System.out.println("流程定义：" + myEvection + ",已激活");
            } else {
                // 如果已经启动，我们就暂停
                repositoryService.suspendProcessDefinitionById(myEvection.getId(), true, null);
                System.out.println("流程定义：" + myEvection + ",已挂起");
            }
        }
    }

    @Test
    public void testCompleteTask() {
        Task task = taskService
                .createTaskQuery()
                .processInstanceId("4f4ca3d3-8f9e-11ec-a901-30d16bf4f117")
                .taskAssignee("jerry")
                .singleResult();
        System.out.println("流程实例ID : " + task.getProcessInstanceId());
        System.out.println("任务ID : " + task.getId());
        System.out.println("任务负责人 ： " + task.getAssignee());
        System.out.println("任务名称 ： " + task.getName());
        taskService.complete(task.getId());
    }

    /**
     * 查询个人代办任务
     * @author haitao.chen
     * email
     * date 2022/2/17 15:35
     */
    @Test
    public void testFindPersonalTaskList() {
        List<Task> list = taskService
                .createTaskQuery()
                .taskAssignee("张三")
                .processDefinitionKey("Myevection2")
                .includeProcessVariables()
                .list();
        for (Task task : list) {
            System.out.println("----------------------------");
            System.out.println("流程实例id： " + task.getProcessInstanceId());
            System.out.println("任务id： " + task.getId());
            System.out.println("任务负责人： " + task.getAssignee());
            System.out.println("任务名称： " + task.getName());
        }
    }
}
