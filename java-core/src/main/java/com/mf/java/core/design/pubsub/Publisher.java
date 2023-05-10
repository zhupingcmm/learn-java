package com.mf.java.core.design.pubsub;

public interface Publisher {

    void register(Listener listener);

    void pushMessage(String message);

    void  unRegister(Listener listener);

}
