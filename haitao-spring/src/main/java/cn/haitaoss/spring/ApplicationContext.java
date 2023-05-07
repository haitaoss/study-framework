package cn.haitaoss.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-31 10:56
 *
 */
public class ApplicationContext {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ApplicationContext(Class<?> clazz) {
        if (clazz.isAnnotationPresent(ComponentScan.class)) {
            // 扫描bean
            ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
            String packageName = componentScan.value();
            scanPackage(packageName);

            // 创建单例bean
            for (String beanName : beanDefinitionMap.keySet()) {
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

                if (beanDefinition.getScope()
                        .equals("prototype")) {
                    continue;
                }

                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);

            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        // 推断构造器
        try {
            Object obj = beanDefinition.getType()
                    .getConstructor()
                    .newInstance();

            // 属性注入
            for (Field declaredField : obj.getClass()
                    .getDeclaredFields()) {

                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    declaredField.setAccessible(true);

                    declaredField.set(obj, getBean(declaredField.getName()));
                }


            }

            // aware
            if (BeanNameAware.class.isAssignableFrom(obj.getClass())) {
                ((BeanNameAware) obj).setBeanName(beanName);
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                obj = beanPostProcessor.postProcessBeforeInitialization(beanName, obj);
            }

            // 初始化方法
            if (InitializingBean.class.isAssignableFrom(obj.getClass())) {
                ((InitializingBean) obj).afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                obj = beanPostProcessor.postProcessAfterInitialization(beanName, obj);
            }

            return obj;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    void scanPackage(String packageName) {
        String path = packageName.replace(".", "/");

        File file = new File(ClassLoader.getSystemResource(path)
                .getFile());

        List<String> clazzList = new ArrayList<>();
        recursiveClass(file, clazzList);

        clazzList.stream()
                .map(item -> item.substring(item.lastIndexOf(path)))
                .filter(item -> item.endsWith("class"))
                .forEach(item -> {
                    String clazzName = item.replace("/", ".");
                    clazzName = clazzName.replace(".class", "");
                    try {
                        Class<?> aClass = ClassLoader.getSystemClassLoader()
                                .loadClass(clazzName);

                        if (aClass.isAnnotationPresent(Component.class)) {

                            String beanName = Introspector.decapitalize(aClass.getSimpleName());
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setBeanName(beanName);
                            beanDefinition.setType(aClass);
                            beanDefinition.setScope(Optional.ofNullable(aClass.getAnnotation(Scope.class))
                                    .map(Scope::value)
                                    .orElse("singleton"));

                            beanDefinitionMap.put(beanName, beanDefinition);

                            if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                                beanPostProcessorList.add((BeanPostProcessor) createBean(beanName, beanDefinition));
                            }
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void recursiveClass(File file, List<String> clazzList) {
        if (file.isFile()) {
            clazzList.add(file.getPath());
            return;
        }

        for (File tmpFile : file.listFiles()) {
            recursiveClass(tmpFile, clazzList);
        }
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null) {
            throw new RuntimeException();
        }

        if (beanDefinition.getScope()
                .equals("prototype")) {
            return createBean(beanName, beanDefinition);
        }

        Object bean = singletonObjects.get(beanName);
        if (bean != null) {
            return bean;
        }

        bean = createBean(beanName, beanDefinition);
        singletonObjects.put(beanName, bean);

        return bean;
    }
}
