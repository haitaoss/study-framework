package cn.haitaoss.parametricdynamicdatasource.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 18:59
 *
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * 返回上下文
     */
    public static ApplicationContext getContext() {
        return SpringContextHolder.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
