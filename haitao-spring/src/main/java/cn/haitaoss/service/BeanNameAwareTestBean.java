package cn.haitaoss.service;

import cn.haitaoss.spring.BeanNameAware;
import cn.haitaoss.spring.Component;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 15:59
 *
 */
@Component
public class BeanNameAwareTestBean implements BeanNameAware {
    private String beanName;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    public void sayBeanName() {
        System.out.println("beanName = " + beanName);
    }
}
