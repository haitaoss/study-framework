package cn.haitaoss;

import cn.haitaoss.entity.Person;

public class Hello {
    public static void main(String[] args) {
        Person person = new Person("haiao", 22);
        System.out.println("person = " + person);
        System.out.println("args = " + args);
    }
}