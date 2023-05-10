package com.mf.java.core.design.template;

public abstract class AbstractTemplatePattern implements TemplatePattern{
    @Override
    public void step1() {
        System.out.println("step 1");
    }

    @Override
    public void step2() {
        System.out.println("step 2");
    }

    @Override
    public void step3() {
        System.out.println("step 3");
    }

    @Override
    public void step4() {
        System.out.println("step 4");
    }

    public void make() {
        step1();
        step2();
        step3();
        step4();
    };

    public abstract void run();
}
