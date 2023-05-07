package cn.haitaoss.basicmultidatasource.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-02-19 09:59
 *
 */
@Configuration
@MapperScan(basePackages = "cn.haitaoss.basicmultidatasource.mapper.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterMybatisConfig {
    /**
     * 注意，此处需要使用MybatisSqlSessionFactoryBean，不是SqlSessionFactoryBean，
     * 否则，使用mybatis-plus的内置函数时就会报invalid bound statement (not found)异常
     */
    @Bean("masterSqlSessionFactory")
    @Primary // 容器中出现同一类型的Bean 需要使用@primary 指定这个为主
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("master") DataSource dataSource) throws Exception {
        // 设置数据源
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        //mapper的xml文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String locationPattern = "classpath*:/mapper/master/*.xml";
        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));
        //对应数据库的entity位置
        String typeAliasesPackage = "cn.haitaoss.basicmultidatasource.mapper.master";
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return mybatisSqlSessionFactoryBean.getObject();
    }
}
