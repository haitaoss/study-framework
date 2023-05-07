package cn.haitaoss.studyactiviti.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 出差申请 pojo
 */
@Data
public class Evection implements Serializable {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 出差申请单名称
     */
    private String evectionName;
    /**
     * 出差天数
     */
    private Double num;
    /**
     * 预计开始时间
     */
    private Date beginDate;
    /**
     * 预计结束时间
     */
    private Date endDate;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 出差事由
     */
    private String reson;
}