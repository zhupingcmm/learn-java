package com.mf.java.core.reflect.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    public Object createObject(Class<?> clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ProxyInvocationHandler(clazz) );
    }

    public static class ProxyInvocationHandler implements InvocationHandler {

        private Class<?> clazz;

        public ProxyInvocationHandler(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("执行代理对象 方法之前");
            Object result = method.invoke(clazz.newInstance(), args);
            System.out.println("执行代理对象 方法之后");
            return result;
        }
    }
}
