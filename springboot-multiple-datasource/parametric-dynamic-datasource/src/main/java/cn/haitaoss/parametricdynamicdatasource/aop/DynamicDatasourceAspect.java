package cn.haitaoss.parametricdynamicdatasource.aop;

import cn.haitaoss.parametricdynamicdatasource.annotation.DS;
import cn.haitaoss.parametricdynamicdatasource.constant.DataSourceConstants;
import cn.haitaoss.parametricdynamicdatasource.context.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 13:56
 * TODO 为什么不能代理类
 */
@Component
@Aspect
@Slf4j
public class DynamicDatasourceAspect {
    /**
     * @annotation 是对方法上的注解进行切面，注解写在类上是没用的 也就是说通过@annotation进行切入点 是切不到类上面的
     * 所以下面的切入点的意思是：对使用的DS注解的方法进行切面 或者 在impl 包下面的所有方法
     * @author haitao.chen
     * email
     * date 2022/2/19 5:27 PM
     */
    @Pointcut(value = "@annotation(cn.haitaoss.parametricdynamicdatasource.annotation.DS) || execution(* cn.haitaoss.parametricdynamicdatasource.service..*(..))")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object methodInvokeBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("dataSourcePointCut..... start");
        try {
            String value = DataSourceConstants.DS_KEY_MASTER;
            DS dsAnnotation = getDSAnnotation(joinPoint);
            if (Objects.nonNull(dsAnnotation)) {
                value = dsAnnotation.value();
            }
            // 设置数据源
            DynamicDataSourceContextHolder.setContextKey(value);
            // 执行业务方法
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
            log.info("dataSourcePointCut..... end");
        }
    }

    private DS getDSAnnotation(ProceedingJoinPoint joinPoint) {
        // 通过方法获取
        DS ds = ((MethodSignature) joinPoint.getSignature())
                .getMethod()
                .getAnnotation(DS.class);
        if (Objects.nonNull(ds)) {
            return ds;
        }
        //  通过类获取
        DS annotation = joinPoint
                .getTarget()
                .getClass()
                .getAnnotation(DS.class);
        if (Objects.nonNull(annotation)) {
            return annotation;
        }
        return null;
    }
}
