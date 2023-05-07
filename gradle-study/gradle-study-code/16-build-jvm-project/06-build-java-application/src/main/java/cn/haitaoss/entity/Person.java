package cn.haitaoss.entity;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-03-11 14:13
 *
 */
public class Person {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
