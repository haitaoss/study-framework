package cn.haitaoss.studyspringbooturule.service;

import cn.haitaoss.studyspringbooturule.entity.Calculation;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-13 14:25
 *
 */
public interface ICalculationService {
    public Calculation calculate(Double wage) throws Exception;

    public Calculation calculate2(Double wage) throws Exception;

    public void calculate3() throws Exception;
}
