package cn.haitaoss.parametricdynamicdatasource;

import cn.haitaoss.parametricdynamicdatasource.aop.CgLibParamDsMethodProxy;
import cn.haitaoss.parametricdynamicdatasource.context.DynamicDataSourceContextHolder;
import cn.haitaoss.parametricdynamicdatasource.mapper.TableMapper;
import cn.haitaoss.parametricdynamicdatasource.service.IUserService;
import cn.haitaoss.parametricdynamicdatasource.util.DataSourceUtil;
import cn.haitaoss.parametricdynamicdatasource.vo.DbInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ParametricDynamicDatasourceApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private TableMapper tableMapper;

    @Test
    public void test() {
        DbInfo dbInfo = new DbInfo();
        dbInfo.setIp("localhost");
        dbInfo.setPort("3306");
        dbInfo.setDbName("jdbc");
        dbInfo.setUsername("root");
        dbInfo.setPassword("root");

        // TableMapper dynamicInstance = (TableMapper) JdkParamDsMethodProxy.getDynamicInstance(tableMapper, "xxx", dbInfo);
        TableMapper dynamicInstance = (TableMapper) CgLibParamDsMethodProxy.getDynamicInstance(tableMapper, "xxx", dbInfo);
        List<Map<String, Object>> maps = dynamicInstance.selectTableList();

        //查询表信息
        for (Map<String, Object> table : maps) {
            table.forEach((key, value) -> System.out.println(key + "---" + value));
        }
    }

    @Test
    void contextLoads() throws Exception {
        //数据源key
        String newDsKey = System.currentTimeMillis() + "";
        DbInfo dbInfo = new DbInfo();
        dbInfo.setIp("localhost");
        dbInfo.setPort("3306");
        dbInfo.setDbName("jdbc");
        dbInfo.setUsername("root");
        dbInfo.setPassword("root");


        //添加数据源
        DataSourceUtil.addDataSourceToDynamic(newDsKey, dbInfo);
        DynamicDataSourceContextHolder.setContextKey(newDsKey);

        //查询表信息
        List<Map<String, Object>> tables = tableMapper.selectTableList();
        DynamicDataSourceContextHolder.removeContextKey();
        for (Map<String, Object> table : tables) {
            table.forEach((key, value) -> System.out.println(key + "---" + value));
        }

    }

}
