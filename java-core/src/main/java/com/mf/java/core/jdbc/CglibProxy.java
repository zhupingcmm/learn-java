package com.mf.java.core.jdbc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object object;
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        System.out.println("cglib proxy");
        if (method.getName().equals("commit")) {
            System.out.println("try to commit into table");
        }
        return proxy.invoke(object, args);
    }

    public <T> T getProxy(T object){
        this.object = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }
}
