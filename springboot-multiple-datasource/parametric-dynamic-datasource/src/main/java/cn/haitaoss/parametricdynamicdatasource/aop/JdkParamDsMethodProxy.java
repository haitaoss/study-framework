package cn.haitaoss.parametricdynamicdatasource.aop;


import cn.haitaoss.parametricdynamicdatasource.context.DynamicDataSourceContextHolder;
import cn.haitaoss.parametricdynamicdatasource.util.DataSourceUtil;
import cn.haitaoss.parametricdynamicdatasource.vo.DbInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 19:44
 *
 */
public class JdkParamDsMethodProxy implements InvocationHandler {
    private Object targetObject;
    private String dataSourceKey;
    private DbInfo dbInfo;

    private JdkParamDsMethodProxy(Object targetObject, String dataSourceKey, DbInfo dbInfo) {
        this.targetObject = targetObject;
        this.dbInfo = dbInfo;
        this.dataSourceKey = dataSourceKey;
    }

    public static Object getDynamicInstance(Object targetObject, String dataSourceKey, DbInfo dbInfo) {
        return Proxy.newProxyInstance(JdkParamDsMethodProxy.class
                .getClassLoader(), targetObject
                .getClass()
                .getInterfaces(), new JdkParamDsMethodProxy(targetObject, dataSourceKey, dbInfo));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 添加数据源
        DataSourceUtil.addDataSourceToDynamic(dataSourceKey, dbInfo);

        // 使用数据源
        DynamicDataSourceContextHolder.setContextKey(dataSourceKey);

        // 执行方法
        Object invoke = method.invoke(targetObject, args);

        // 删除动态设置的源
        DynamicDataSourceContextHolder.removeContextKey();
        return invoke;
    }
}
