package com.mf.spring01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Aop2 {

    @Pointcut(value = "execution(* com.mf.spring01.ZClass.*dong(..))")
    public void point() {}

    @Before(value = "point()")
    public void before(){
        System.out.println("======> begin zClass dong");
    }

    @Around(value = "point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("======>around begin zClass dong");
        joinPoint.proceed();
        System.out.println("======>around after zClass dong");
    }


    @After(value = "point()")
    public void after(){
        System.out.println("======> after zClass dong");
    }
}
