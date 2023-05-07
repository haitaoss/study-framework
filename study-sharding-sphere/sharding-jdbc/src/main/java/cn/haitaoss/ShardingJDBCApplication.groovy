package cn.haitaoss

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-05-10 09:07
 *
 */
@SpringBootApplication
@MapperScan("cn.haitaoss.mapper")
class ShardingJDBCApplication {
    static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApplication.class, args)
    }
}
