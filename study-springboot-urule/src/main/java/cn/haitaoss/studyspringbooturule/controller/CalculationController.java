package cn.haitaoss.studyspringbooturule.controller;

import cn.haitaoss.studyspringbooturule.entity.Calculation;
import cn.haitaoss.studyspringbooturule.service.impl.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-13 14:21
 *
 */
@RestController
public class CalculationController {
    @Autowired
    private CalculationService calculationService;

    @RequestMapping("/exeRule/{wage}")
    public ResponseEntity exeRule(@PathVariable("wage") Double wage) throws Exception {
        Calculation calculate = calculationService.calculate(wage);
        return ResponseEntity.ok(calculate);
    }

    @RequestMapping("/exeFlow/{wage}")
    public ResponseEntity exeFlow(@PathVariable("wage") Double wage) throws Exception {
        Calculation calculate = calculationService.calculate2(wage);
        return ResponseEntity.ok(calculate);
    }

    @RequestMapping("/exeFlowWithParam")
    public ResponseEntity exeFlowWithParam() throws Exception {
        calculationService.calculate3();
        return ResponseEntity
                .ok()
                .build();
    }
}
