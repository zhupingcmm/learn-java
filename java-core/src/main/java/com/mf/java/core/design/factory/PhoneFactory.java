package com.mf.java.core.design.factory;

import com.mf.java.core.design.factory.impl.IPhone;
import com.mf.java.core.design.factory.impl.XiaoMi;

public class PhoneFactory {
    public Phone makePhone(String phoneType) {
        if ("xiaomi".equals(phoneType)) {
            return new XiaoMi();
        } else if ("iphone".equals(phoneType)) {
            return new IPhone();
        }
        return null;
    }
}
