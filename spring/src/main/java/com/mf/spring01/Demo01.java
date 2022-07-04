package com.mf.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo01 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());
        student123.print();

        System.out.println("=================student100===================");
        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());
        student100.print();


        System.out.println("=================ZClass===================");

        ZClass zClass = (ZClass) context.getBean(ZClass.class);
        zClass.dong();

//        System.out.println("=================School===================");
//        ISchool school = (School) context.getBean("school");
//        school.ding();

    }
}
