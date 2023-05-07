package cn.haitaoss.studyactiviti.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-14 17:17
 *
 */
@RestController
@RequestMapping("/evection")
public class EvectionProcessController {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /*
     * 部署流程（相当于定义好规则）
     * @author haitao.chen
     * email
     * date 2022/2/14 17:28
     * @return null
     */
    // @PostConstruct
    public void deployProcess() {
        System.out.println("部署bpmn 文件....");
        Deployment deploy = repositoryService
                .createDeployment()
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .name("出差审批")
                .deploy();
        System.out.println("流程部署ID: " + deploy.getId());
        System.out.println("流程部署name: " + deploy.getName());
    }

    /**
     * 启动流程实例（相当于使用规则）
     * @author haitao.chen
     * email
     * date 2022/2/14 17:28
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("/start")
    public ResponseEntity startProcess() {
        // 启动流程实例，id 就是在部署流程文件中定义的
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("myEvection");
        System.out.println("流程实例ID: " + processInstance.getId());
        System.out.println("流程实例key: " + processInstance.getProcessDefinitionKey());
        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 查询某人的任务
     * @author haitao.chen
     * email
     * date 2022/2/14 17:42
     * @param assignee
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("/query/{assignee}")
    public ResponseEntity queryProcess(@PathVariable("assignee") String assignee) {
        Task task = taskService
                .createTaskQuery()
                .taskAssignee(assignee)
                .singleResult();

        System.out.println("任务ID: " + task.getId());
        System.out.println("任务处理人: " + task.getAssignee());
        System.out.println("流程实例ID: " + task.getProcessDefinitionId());
        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 完成任务
     * @author haitao.chen
     * email
     * date 2022/2/14 17:42
     * @param assignee
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("/complete/{assignee}")
    public ResponseEntity completeProcess(@PathVariable("assignee") String assignee) {
        Task task = taskService
                .createTaskQuery()
                .taskAssignee(assignee)
                .singleResult();
        // 进行下一步流程
        taskService.complete(task.getId());
        return ResponseEntity
                .ok()
                .build();
    }
}
