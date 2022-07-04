package com.mf.spring01;



import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.Arrays;

public class Student implements Serializable, BeanNameAware, ApplicationContextAware {
    @Setter
    private int id;
    @Setter
    private String name;

    @Setter
    private String beanName;

    private ApplicationContext applicationContext;
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void print() {
        System.out.println("beanName:" + this.beanName);
        System.out.println("applicationContext.getBeanDefinitionNames() =>" + Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    public void init() {
        System.out.println(this.beanName + " has been init");
    }

    public void destroy() {
        System.out.println(this.beanName + " has been destory");
    }


}
