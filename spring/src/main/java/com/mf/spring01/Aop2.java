package com.mf.spring01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop2 {

    @Pointcut(value = "execution(* com.mf.spring01.ZClass.*dong(..))")
    public void point() {}

    @Before(value = "point()")
    public void before(){
        System.out.println("[before]======> zClass dong");
    }

    @Around(value = "point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[around before]======> begin zClass dong");
        joinPoint.proceed();
        System.out.println("[around after] ======> after zClass dong");
    }


    @After(value = "point()")
    public void after(){
        System.out.println("[after]======>  zClass dong");
    }

    @AfterReturning(value = "point()")
    public void afterReturn() {
        System.out.println("[afterReturn]======> zClass dong");
    }
}
