package cn.haitaoss.studyactiviti;

import cn.haitaoss.studyactiviti.entity.Evection;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-17 16:11
 * 流程变量的作用域可以是一个流程实例(processInstance)，或一个任务(task)，或一个执行实例
 * (execution)
 * 	global 变量：流程变量的默认作用域是流程实例。当一个流程变量的作用域为流程实例时，可以称为 global 变量
 * 	local 变量：任务和执行实例仅仅是针对一个任务和一个执行实例范围，范围没有流程实例大， 称为 local 变量。
 *
 * 	流程变量设置方式：
 * 		- global 变量：
 * 			- 可以在启动流程时设置  runtimeService.startProcessInstanceByKey(key, map);
 * 			- 也可以在任务办理时设置（该流程变量只有在该任务完成后其它结点才可使用该变量，它的作用域是整个流程实例） taskService.complete(task.getId(),map);
 * 			- 通过流程实例id设置全局变量，该流程实例必须未执行完成。  runtimeService.setVariables(processInstanceId, variables)
 * 			- 通过当前任务设置 taskService.setVariables(taskId, variables)
 * 		- local 变量：
 * 			- 通过当前任务设置 taskService.setVariablesLocal(taskId, variables)
 *
 *
 * 	流程变量使用方式：在bpmn绘图中，使用表达式获取流程变量的值 ${paramName},${obj.computeNum()},${object.name}
 */
@SpringBootTest
public class FiveActivitiProcessVariable {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    @Test
    public void testStartProcess() {
        // 部署流程文件
        repositoryService
                .createDeployment()
                .addClasspathResource("bpmn/evection4.bpmn")
                .addClasspathResource("bpmn/evection4.png")
                .name("出差申请(学习路程变量的使用)")
                .deploy();

        // 定义流程需要的变量
        Evection evection = new Evection();
        evection.setNum(1D);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("evection", evection);
        paramMap.put("assignee0", "张三");
        paramMap.put("assignee1", "李经理");
        paramMap.put("assignee2", "王总经理");
        paramMap.put("assignee3", "赵财务");

        // 传入流程变量，启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("evection4", paramMap);
        System.out.println("流程实例名称=" + processInstance.getName());
        System.out.println("流程定义id==" + processInstance.getProcessDefinitionId());
        // 完成任务
        Task task = taskService
                .createTaskQuery()
                .processDefinitionKey("evection4")
                .taskAssignee("张三")
                .singleResult();
        taskService.complete(task.getId());
    }
}
