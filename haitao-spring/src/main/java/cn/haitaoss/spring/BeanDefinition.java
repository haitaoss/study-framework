package cn.haitaoss.spring;

import lombok.Data;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 15:21
 *
 */
@Data
public class BeanDefinition {
    private String beanName;
    private Class type;
    private String scope;
}

