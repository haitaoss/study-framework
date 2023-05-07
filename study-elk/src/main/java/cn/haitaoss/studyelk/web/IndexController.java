package cn.haitaoss.studyelk.web;

import cn.haitaoss.studyelk.util.InputMDC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-26 18:17
 *
 */
@Slf4j
@RestController
public class IndexController {
    @RequestMapping(value = "/test_es")
    public String test_es() {
        InputMDC.putMDC();

        log.info("hello elk....");

        return "idx";
    }

    @RequestMapping(value = "/index")
    public String index() {
        InputMDC.putMDC();

        log.info("我是一条info日志");

        log.warn("我是一条warn日志");

        log.error("我是一条error日志");

        return "idx";
    }


    @RequestMapping(value = "/err")
    public String err() {
        InputMDC.putMDC();
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            log.error("算术异常", e);
        }
        return "err";
    }

}
