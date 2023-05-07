package cn.haitaoss.multidatasource.annotation;

import cn.haitaoss.multidatasource.constant.DataSourceConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 13:51
 * 动态数据源，使用这个注解 可以指定数据源是什么
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
    /**
     * 数据源的名字
     */
    String value() default DataSourceConstants.DS_KEY_MASTER;
}
