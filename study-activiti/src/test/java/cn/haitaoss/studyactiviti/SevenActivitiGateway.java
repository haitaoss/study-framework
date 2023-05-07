package cn.haitaoss.studyactiviti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-18 10:36
 * 网关用来控制流程的流向:
 * 		排他网关ExclusiveGateway:
 * 				排他网关，用来在流程中实现决策。排他网关只会选择一个为true的分支执行。如果有两个分支条件都为true，排他网关会选择id值较小的一条分支去执行。
 * 											不用排他网关也可以实现分支，如：在连线的condition条件上设置分支条件。在连线设置condition条件的缺点：如果条件都不满足，流程就结束了(是异常结束)。
 *
 * 		并行网关ParallelGateway:
 * 				并行网关允许将流程分成多条分支，也可以把多条分支汇聚到一起，并行网关的功能是基于进入和外出顺序流的。
 * 				与其他网关的主要区别是，并行网关不会解析条件。即使顺序流中定义了条件，也会被忽略。
 *
 *
 * 		包含网关InclusiveGateway:
 * 				包含网关可以看做是排他网关和并行网关的结合体。和排他网关一样，你可以在外出顺序流上定义条件，包含网关会解析它们。 但是主要的区别是包含网关可以选择多于一条顺序流，这和并行网关一样。
 * 				所有并行分支到达包含网关，会进入等待状态， 直到每个包含流程token的进入顺序流的分支都到达。 这是与并行网关的最大不同。换句话说，包含网关只会等待被选中执行了的进入顺序流。 在汇聚之后，流程会穿过包含网关继续执行。
 *
 * 		事件网关EventGateway:
 * 				事件网关允许根据事件判断流向。网关的每个外出顺序流都要连接到一个中间捕获事件。 当流程到达一个基于事件网关，网关会进入等待状态：会暂停执行。与此同时，会为每个外出顺序流创建相对的事件订阅。
 *					事件网关的外出顺序流和普通顺序流不同，这些顺序流不会真的"执行"， 相反它们让流程引擎去决定执行到事件网关的流程需要订阅哪些事件。
 *					要考虑以下条件：
 *							1. 事件网关必须有两条或以上外出顺序流；
 *				 		2. 事件网关后，只能使用intermediateCatchEvent类型（activiti不支持基于事件网关后连接ReceiveTask）
 *				 	 3. 连接到事件网关的中间捕获事件必须只有一个入口顺序流。
 */
@SpringBootTest
public class SevenActivitiGateway {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Test
    public void test() {
				/*deployProcess();
				startProcess();*/

        String taskId = "";
        taskService.complete(taskId);
    }

    private void startProcess() {
        // runtimeService.startProcessInstanceByKey("evection7","测试排他网关业务key");
        runtimeService.startProcessInstanceByKey("evection9", "测试包含网关业务key");
    }

    private void deployProcess() {
        repositoryService
                .createDeployment()
                /*.addClasspathResource("bpmn/evection7.bpmn")
                .addClasspathResource("bpmn/evection7.png")*/
                /*.addClasspathResource("bpmn/evection8.bpmn")
                .addClasspathResource("bpmn/evection8.png")*/
                .addClasspathResource("bpmn/evection9.bpmn")
                .addClasspathResource("bpmn/evection9.png")
                // .name("测试排他网关")
                // .name("测试并行网关")
                .name("测试包容网关")
                .deploy();
    }
}
