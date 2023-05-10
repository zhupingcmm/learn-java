package com.op;

import com.mf.spring01.Student;
import lombok.val;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Student student = context.getBean(Student.class);
//        System.out.println(student.getId());

        val person = context.getBean(Person.class);
        System.out.println(person.getAge());
    }
}
