package cn.haitaoss.studyspringbooturule.service.impl;

import cn.haitaoss.studyspringbooturule.entity.Calculation;
import cn.haitaoss.studyspringbooturule.service.ICalculationService;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.KnowledgeSessionFactory;
import com.bstek.urule.runtime.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-13 13:37
 *
 */
@Service
public class CalculationService implements ICalculationService {
    @Autowired
    @Qualifier(KnowledgeService.BEAN_ID)
    private KnowledgeService service;

    public Calculation calculate(Double wage) throws Exception {
        //通过KnowledgeService接口获取指定的知识包ID"213"
        KnowledgePackage knowledgePackage = service.getKnowledge("1");
        //通过取到的KnowledgePackage对象创建KnowledgeSession对象
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);

        Calculation calculation = new Calculation();
        calculation.setWage(wage);
        //将业务数据对象Employee插入到KnowledgeSession中
        session.insert(calculation);
        //执行所有满足条件的规则
        session.fireRules(); // 计算结果会回填到 插入的变量中

        return calculation;
    }

    public Calculation calculate2(Double wage) throws Exception {
        //通过KnowledgeService接口获取指定的知识包ID"213"
        KnowledgePackage knowledgePackage = service.getKnowledge("101");
        //通过取到的KnowledgePackage对象创建KnowledgeSession对象
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);

        Calculation calculation = new Calculation();
        calculation.setWage(23456D);
        //将业务数据对象Employee插入到KnowledgeSession中
        session.insert(calculation);
        //执行决策流
        session.startProcess("calculation"); // 计算结果会回填到 插入的变量中
        return calculation;
    }

    public void calculate3() throws Exception {
        //通过KnowledgeService接口获取指定的知识包ID"213"
        KnowledgePackage knowledgePackage = service.getKnowledge("201");
        //通过取到的KnowledgePackage对象创建KnowledgeSession对象
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);

        HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("name", "海涛");
        stringStringHashMap.put("address", "df");
        //执行决策流
        session.startProcess("test_param", stringStringHashMap); // 计算结果会回填到 插入的变量中
        session
                .getParameters()
                .forEach((key, value) -> {
                    System.out.println("key = " + key + " , value = " + value);
                });
    }
}
