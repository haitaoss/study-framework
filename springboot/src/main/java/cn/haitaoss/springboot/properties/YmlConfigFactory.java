package cn.haitaoss.springboot.properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-08-16 21:22
 */
public class YmlConfigFactory extends DefaultPropertySourceFactory {
    public YmlConfigFactory() {
        System.out.println("YmlConfigFactory 构造器");
    }

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String sourceName = Optional.ofNullable(name)
                .orElse(resource.getResource()
                        .getFilename());

        if (!resource.getResource()
                .exists()) {
            return new PropertiesPropertySource(sourceName, new Properties());
        } else if (Stream.of(".yml", ".yaml")
                .anyMatch(sourceName::endsWith)) {
            Properties properties = loadYml(resource); // 增加对 yml 格式的解析
            return new PropertiesPropertySource(sourceName, properties);
        } else {
            return super.createPropertySource(name, resource);
        }

    }

    private Properties loadYml(EncodedResource resource) {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}

