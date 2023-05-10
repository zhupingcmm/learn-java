package com.mf.java.core.design.pubsub.impl;

import com.mf.java.core.design.pubsub.Listener;

public class AListenerImpl implements Listener {
    @Override
    public void onMessage(String message) {
        System.out.println("A" + message);
    }

}
