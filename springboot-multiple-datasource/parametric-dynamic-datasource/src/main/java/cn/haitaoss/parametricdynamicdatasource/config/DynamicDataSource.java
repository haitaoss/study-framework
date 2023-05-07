package cn.haitaoss.parametricdynamicdatasource.config;

import cn.haitaoss.parametricdynamicdatasource.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 11:26
 * AbstractRoutingDataSource 实现了 InitializingBean 接口
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private Map<Object, Object> backTargetDataSources;

    public DynamicDataSource(Map<Object, Object> targetDataSources, DataSource defaultDatasource) {
        this.backTargetDataSources = targetDataSources;
        setTargetDataSources(targetDataSources);
        setDefaultTargetDataSource(defaultDatasource);
        afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }

    public void addDatasource(String key, DataSource dataSource) {
        backTargetDataSources.put(key, dataSource);
        setTargetDataSources(backTargetDataSources);

        // 更新数据源信息
        afterPropertiesSet();
    }
}
