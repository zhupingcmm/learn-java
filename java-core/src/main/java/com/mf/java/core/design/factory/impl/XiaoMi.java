package com.mf.java.core.design.factory.impl;

import com.mf.java.core.design.factory.Phone;

public class XiaoMi implements Phone {
    @Override
    public void make() {
        System.out.println("xiaomi");
    }
}
