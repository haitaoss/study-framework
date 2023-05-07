package cn.haitaoss.parametricdynamicdatasource.aop;

import cn.haitaoss.parametricdynamicdatasource.context.DynamicDataSourceContextHolder;
import cn.haitaoss.parametricdynamicdatasource.util.DataSourceUtil;
import cn.haitaoss.parametricdynamicdatasource.vo.DbInfo;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 20:04
 *
 */
public class CgLibParamDsMethodProxy {
    private Object targetObject;
    private String dynamicDataSourceKey;
    private DbInfo dbInfo;

    private CgLibParamDsMethodProxy(Object targetObject, String dynamicDataSourceKey, DbInfo dbInfo) {
        this.targetObject = targetObject;
        this.dynamicDataSourceKey = dynamicDataSourceKey;
        this.dbInfo = dbInfo;

        getDynamicInstance(targetObject, dynamicDataSourceKey, dbInfo);
    }

    public static Object getDynamicInstance(Object targetObject, String dynamicDataSourceKey, DbInfo dbInfo) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(targetObject
                .getClass()
                .getInterfaces());
        enhancer.setClassLoader(CgLibParamDsMethodProxy.class.getClassLoader());


        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                // 添加数据源
                DataSourceUtil.addDataSourceToDynamic(dynamicDataSourceKey, dbInfo);
                DynamicDataSourceContextHolder.setContextKey(dynamicDataSourceKey);

                Object invoke = method.invoke(targetObject);

                // 删除动态配置
                DynamicDataSourceContextHolder.removeContextKey();
                return invoke;
            }
        });

        return enhancer.create();
    }

}
