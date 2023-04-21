package com.mf.java.core.threads;

public class Demo05Synchronized {
    public synchronized void increase() {
        System.out.println("synchronized method");
    }

    public void syncBlock() {
        synchronized (this) {
            System.out.println("synchronized block");
        }
    }
}
