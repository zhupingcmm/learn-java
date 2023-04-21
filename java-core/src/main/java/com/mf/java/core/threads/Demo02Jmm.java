package com.mf.java.core.threads;

public class Demo02Jmm {
    public static void main(String[] args) {
        JmmDemo jmmDemo = new JmmDemo();
        Thread thread = new Thread();
        thread.start();
        jmmDemo.flag = false;
        System.out.println("flag已经修改为false");
        System.out.println(jmmDemo.flag);
    }
    static class JmmDemo implements Runnable{
        public boolean flag = true;
        @Override
        public void run() {
            System.out.println("子线程开始指向...");
            while (flag) {

            }

            System.out.println("子线程结束....");

        }
    }
}
