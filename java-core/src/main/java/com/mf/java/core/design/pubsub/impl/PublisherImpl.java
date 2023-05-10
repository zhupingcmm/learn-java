package com.mf.java.core.design.pubsub.impl;

import com.mf.java.core.design.pubsub.Listener;
import com.mf.java.core.design.pubsub.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherImpl implements Publisher {

    private List<Listener> listeners = new ArrayList<>();

    @Override
    public void register(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void pushMessage(String message) {
        listeners.forEach(listener -> listener.onMessage(message));
    }

    @Override
    public void unRegister(Listener listener) {
        for (int i = 0; i < listeners.size(); i++) {
            if (listeners.get(i) == listener) {
                listeners.remove(i);
            }
        }
    }
}
