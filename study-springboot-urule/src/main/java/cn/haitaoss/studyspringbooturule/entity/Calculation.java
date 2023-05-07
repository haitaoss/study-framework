package cn.haitaoss.studyspringbooturule.entity;

import lombok.Data;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-13 12:21
 *
 */
@Data
public class Calculation {
    // @Label("扣税额")
    private Double actualWage;
    // @Label("扣税额")
    private Double cess;
    // @Label("扣税额")
    private Double wage;
    // @Label("扣税额")
    private Double wageMinus;
    // @Label("扣税额")
    private Double wageMore;
    // @Label("扣税额")
    private Double preminus;
}
