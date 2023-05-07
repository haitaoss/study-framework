package cn.haitaoss.springboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-08-15 18:32
 */
@Data
@ConfigurationProperties(prefix = "test")
@PropertySource(value = "classpath:test.yml", factory = YmlConfigFactory.class)
@Component
public class MyProperties {
    public MyProperties() {
        System.out.println("MyProperties 构造器");
    }

    private String ip;
    private String searchUrl;
    private Map<String, String> taskMap;
}
