package cn.haitaoss.spring;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 16:05
 *
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(String beanName, Object bean);

    Object postProcessAfterInitialization(String beanName, Object bean);
}
