package cn.haitaoss.studyactiviti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-17 15:18
 * 任务监听器是发生对应的任务相关事件时执行自定义 java 逻辑 或表达式
 * 	支持的监听时机：
 * 		Create：任务创建后触发
 * 		Assignment：任务分配后触发
 * 		Delete：任务完成后触发
 * 		All：所有事件发生都触发
 *
 * 	需要配合bpmn 插件的时候，再画bpmn 的时候指定使用任务监听器，由于我目前使用的IDEA插件没有这个功能所以不做了
 *
 */
@SpringBootTest
public class ThreeActivitiDynamicAssigneeByTaskListener {
}

// 定义任务监听器
class EvectionThreeTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        if (delegateTask
                .getEventName()
                .equals("create") && delegateTask
                .getName()
                .equals("")) {
            // 指定任务负责人
            delegateTask.setAssignee("任务监听器--张三");
        }
    }
}