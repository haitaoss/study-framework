package cn.haitaoss.studyactiviti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@SpringBootTest
class ZeroStudyActivitiApplicationTests {

    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    @Test
    void contextLoads() {
    }

    /**
     * 部署流程
     * @author haitao.chen
     * email
     * date 2022/2/14 8:29 PM
     */
    @Test
    public void testDeploy() {
        Deployment deploy = repositoryService
                .createDeployment()
                .name("出差审批")
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .deploy();
        System.out.println("部署id = " + deploy.getId());
        System.out.println("部署name = " + deploy.getName());
    }

    /**
     * 启动流程
     * @author haitao.chen
     * email
     * date 2022/2/14 8:29 PM
     */
    @Test
    public void testStartProcess() {
        ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("myEvection");
    }

    /**
     * 查询某个人待执行的任务
     * @author haitao.chen
     * email
     * date 2022/2/14 8:23 PM
     */
    @Test
    public void testFindPersonalTaskList() {
        String name = "zhangsan";
        List<Task> myEvection = taskService
                .createTaskQuery()
                .processDefinitionKey("myEvection") // 流程的key
                .taskAssignee(name) // 要查询的负责人
                .list();
        for (Task task : myEvection) {
            System.out.println("流程实例ID: " + task.getProcessInstanceId());
            System.out.println("任务ID: " + task.getId());
            System.out.println("流程负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());

        }
    }

    /**
     * 完成个人任务
     * @author haitao.chen
     * email
     * date 2022/2/14 8:37 PM
     */
    @Test
    public void testCompleteTask() {
        // taskService.complete("fa56cf4c-8d91-11ec-bcda-ea549dafc40a");

        // 获取jerry的任务，并完成
				/*Task task = taskService
												.createTaskQuery()
												.processDefinitionKey("myEvection")
												.taskAssignee("jerry")
												.singleResult();*/

        // 完成 jack 的任务
				/*Task task = taskService
												.createTaskQuery()
												.processDefinitionKey("myEvection")
												.taskAssignee("jack")
												.singleResult();*/

        // 完成 rose 的审批
        Task task = taskService
                .createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee("rose")
                .singleResult();
        System.out.println("流程实例ID: " + task.getProcessInstanceId());
        System.out.println("任务ID: " + task.getId());
        System.out.println("流程负责人：" + task.getAssignee());
        System.out.println("任务名称：" + task.getName());

        taskService.complete(task.getId());
    }

    /**
     * 使用zip包进行批量的部署
     * @author haitao.chen
     * email
     * date 2022/2/14 9:01 PM
     */
    @Test
    public void testDeployProcessByZip() {
        InputStream ins = ClassLoader.getSystemResourceAsStream("bpmn/evection.zip");
				/*InputStream insins = this
												.getClass()
												.getClassLoader()
												.getResourceAsStream("bpmn/evection.zip");*/
        ZipInputStream zipInputStream = new ZipInputStream(ins);
        Deployment deploy = repositoryService
                .createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();

        System.out.println("流程部署的ID：" + deploy.getId());
        System.out.println("流程部署的name：" + deploy.getName());
    }
}
