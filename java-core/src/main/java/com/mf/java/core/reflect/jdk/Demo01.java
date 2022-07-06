package com.mf.java.core.reflect.jdk;

public class Demo01 {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Person person = (Person) jdkProxy.createObject(PersonImpl.class);
        String name = person.findName();
        System.out.println("jdk proxy find name:" + name);
    }
}
