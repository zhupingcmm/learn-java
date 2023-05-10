package com.mf.java.core.design.pubsub;

import com.mf.java.core.design.pubsub.impl.AListenerImpl;
import com.mf.java.core.design.pubsub.impl.BListenerImpl;
import com.mf.java.core.design.pubsub.impl.PublisherImpl;

public class Test {
    public static void main(String[] args) {
        Publisher publisher = new PublisherImpl();

        Listener a = new AListenerImpl();
        Listener b = new BListenerImpl();
        publisher.register(a);
        publisher.register(b);

        publisher.pushMessage("hkk");
    }
}
