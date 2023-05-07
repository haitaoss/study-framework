package cn.haitaoss.simplelogback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author mason
 * @since 2019/10/31
 */
@SpringBootApplication
@Slf4j
public class LogbackSimpleDemoApplication {

    public static void main(String[] args) {
        // Logger log = LoggerFactory.getLogger(LogbackSimpleDemoApplication.class);
        log.info("info----");
        SpringApplication.run(LogbackSimpleDemoApplication.class, args);
    }

}
