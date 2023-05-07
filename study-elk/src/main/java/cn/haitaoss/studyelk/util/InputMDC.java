package cn.haitaoss.studyelk.util;

import org.slf4j.MDC;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-26 18:18
 *
 */
@Component
public class InputMDC implements EnvironmentAware {

    private static Environment environment;

    public static void putMDC() {
        MDC.put("hostName", NetUtil.getLocalHostName());
        MDC.put("ip", NetUtil.getLocalIp());
        MDC.put("applicationName", environment.getProperty("spring.application.name"));
    }

    @Override
    public void setEnvironment(Environment environment) {
        InputMDC.environment = environment;
    }

}
