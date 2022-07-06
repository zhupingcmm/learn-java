package com.mf.java.core.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    public static  <T> T getProxy ( T obj) {
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{obj.getClass()}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("JDK PROXY");
                return method.invoke(obj, args);
            }
        });
    }
}
