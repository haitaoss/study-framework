// package cn.haitaoss.springboot.config;
//
// import com.baomidou.mybatisplus.core.MybatisConfiguration;
// import com.baomidou.mybatisplus.core.config.GlobalConfig;
// import com.baomidou.mybatisplus.core.injector.AbstractMethod;
// import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
// import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
// import org.springframework.beans.BeansException;
// import org.springframework.beans.PropertyValues;
// import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;
//
// import java.lang.reflect.Field;
// import java.util.List;
//
// /**
//  * @author haitao.chen
//  * email haitaoss@aliyun.com
//  * date 2022-08-15 18:47
//  */
// // @Configuration
// public class AppConfig {
//
//     public static String[] x() {
//         return null;
//     }
//
//     // @Bean
//     public DefaultSqlInjector sqlInjector() {
//         return new DefaultSqlInjector() {
//             @Override
//             public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
//                 List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//                 methodList.add(new AlwaysUpdateSomeColumnById());
//                 return methodList;
//             }
//         };
//     }
//
//     @Bean
//     public InstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor(ApplicationContext applicationContext) {
//         return new InstantiationAwareBeanPostProcessor() {
//             @Override
//             public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
//                 if (SqlSessionFactory.class.isAssignableFrom(bean.getClass())) {
//                     try {
//                         Field f1 = DefaultSqlSessionFactory.class.getDeclaredField("configuration");
//                         f1.setAccessible(true);
//                         Object o1 = f1.get(bean);
//                         Field f2 = MybatisConfiguration.class.getDeclaredField("globalConfig");
//                         f2.setAccessible(true);
//                         Object o2 = f2.get(o1);
//                         Field f3 = GlobalConfig.class.getDeclaredField("sqlInjector");
//                         f3.setAccessible(true);
//                         f3.set(o2, sqlInjector());
//                         // f3.set(o2, applicationContext.getBean(ISqlInjector.class));
//                     } catch (Exception e) {
//
//                     }
//                 }
//                 return null;
//             }
//         };
//     }
//
// }
