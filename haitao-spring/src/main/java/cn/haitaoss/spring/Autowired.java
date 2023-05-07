package cn.haitaoss.spring;

import java.lang.annotation.*;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 15:40
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value() default "";
}
