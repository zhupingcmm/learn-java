package com.mf.java.core.reflect.cglib;

public class Demo02 {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        School school = cglibProxy.getProxy(School.class);
        school.sayName();
    }
}
