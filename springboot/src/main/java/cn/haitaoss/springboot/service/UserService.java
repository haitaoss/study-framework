package cn.haitaoss.springboot.service;

import cn.haitaoss.springboot.config.MybatisConfig;
import cn.haitaoss.springboot.entity.User;
import cn.haitaoss.springboot.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-08-20 19:55
 */
@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {
    public Consumer<String> run = taskDesc -> {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            baseMapper.selectList(null);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(String.format("%s 耗时: %s ms", taskDesc, time));
    };

    public UserService() {
        System.out.println("UserService 构造器");
    }

    public void test() {
        /*System.out.println("执行了");
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getXxphone, "1");

        System.out.println(list(wrapper));*/
        run.accept("不使用事务的情况");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    //    @Transactional
    public void test_transaction() {
        run.accept("使用事务的情况");
    }


    //    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    //    @Transactional
    public void test_dynamic_ds() {
        MybatisConfig.ds.set("d2");
        log.warn("测试动态数据源：d2 == {}", baseMapper.test());

        MybatisConfig.ds.set("d1");
        log.warn("测试动态数据源：d1 == {}", baseMapper.test());


        MybatisConfig.ds.set("d2");
        log.warn("测试动态数据源：d2 == {}", baseMapper.test());
    }

    public void test_transaction2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Runnable runnable = () -> {
            if (!TransactionSynchronizationManager.isSynchronizationActive()) {
                // 让线程进入事务同步状态
                TransactionSynchronizationManager.initSynchronization();
            }
            // 执行sql
            baseMapper.test();
        };
        // 至多创建三个 sqlSession 而已
        Stream.iterate(0, n -> n + 1).limit(10).forEach(item -> executorService.execute(runnable));

        // 销毁线程池，从而让里面的线程也被销毁，而线程所对应的ThreadLocal也被清空
        executorService.shutdown();
    }
}
