package cn.haitaoss.multidatasource.config;

import cn.haitaoss.multidatasource.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 11:26
 * AbstractRoutingDataSource 实现了 InitializingBean 接口
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }
}
