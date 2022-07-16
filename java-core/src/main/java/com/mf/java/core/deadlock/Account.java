package com.mf.java.core.deadlock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;


public class Account {
    ReentrantLock lock = new ReentrantLock();


    @Getter
    @Setter
    private int balance;

    public Account (int balance) {
        this.balance = balance;
    }
    private void transfer(Account target, int amt){
        if (lock.tryLock()){
            try {
                if (target.lock.tryLock()){
                    try {
                        this.balance -= amt;
                        target.balance += amt;
                    } finally {
                        target.lock.unlock();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(200);
        Account b = new Account(200);
        System.out.println("process start");
        Thread t1 = new Thread(() -> {
            int index = 0;
            while (index++ < 10000){
                a.transfer(b, 100);
                System.out.println("a::"+ a.getBalance());
            }
        }, "T1");
        Thread t2 = new Thread(() -> {
            int index = 0;
            while (index++ < 10000){

                b.transfer(a, 100);
                System.out.println("b::"+ b.getBalance());
            }
        }, "T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("process end");
    }
}
