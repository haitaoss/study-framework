package cn.haitaoss.studyspringbooturule;

import cn.haitaoss.studyspringbooturule.service.impl.CalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudySpringbootUruleApplicationTests {

    @Autowired
    CalculationService calculationService;

    @Test
    void contextLoads() throws Exception {
        calculationService.calculate(23456D);
        calculationService.calculate2(23456D);
    }

}
