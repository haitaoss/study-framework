package cn.haitaoss.api;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-08-01 08:42
 */
public class TestIntrospector {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(JavaBean.class);

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            System.out.println(propertyDescriptor.getName());
            System.out.println(propertyDescriptor.getReadMethod());
            System.out.println(propertyDescriptor.getWriteMethod());
            System.out.println("----");
        }

        Introspector.decapitalize("AService") ; // AService
        Introspector.decapitalize("AaService") ; // aaService


        // Arrays.asList("1").stream().collect(Collectors.toMap(k -> {}, v -> {}))
    }


}

class JavaBean {
    private String name;
    public String address;
    private String name2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}