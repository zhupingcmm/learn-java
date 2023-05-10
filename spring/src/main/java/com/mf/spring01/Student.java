package com.mf.spring01;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.Arrays;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student implements Serializable {

    private int id;

    private String name;


    private String beanName;


    public void print() {
        System.out.println("beanName:" + this.beanName);
    }

    public void init() {
        System.out.println(this.beanName + " has been init");
    }

    public void destroy() {
        System.out.println(this.beanName + " has been destroy");
    }

}
