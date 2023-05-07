package cn.haitaoss.service;

import cn.haitaoss.spring.Component;
import cn.haitaoss.spring.InitializingBean;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 16:02
 *
 */
@Component
public class InitializingBeanTest implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("execute afterPropertiesSet...");
    }
}
