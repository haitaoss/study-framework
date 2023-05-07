package cn.haitaoss.studyspringbooturule.event;

import com.bstek.urule.model.flow.FlowNode;
import com.bstek.urule.model.flow.NodeEvent;
import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.ProcessInstance;
import org.springframework.stereotype.Component;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-13 13:54
 *
 */
@Component
public class CalculationStrategyEvent implements NodeEvent {
    @Override
    public void enter(FlowNode flowNode, ProcessInstance processInstance, FlowContext flowContext) {
        System.out.println("决策 start...");
    }

    @Override
    public void leave(FlowNode flowNode, ProcessInstance processInstance, FlowContext flowContext) {
        System.out.println("决策 end...");
    }
}
