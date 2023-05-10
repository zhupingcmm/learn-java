package com.mf.java.core.design.factory;

public class Test {
    public static void main(String[] args) {
        PhoneFactory factory = new PhoneFactory();
        Phone phone = factory.makePhone("xiaomi");
        phone.make();
    }
}
