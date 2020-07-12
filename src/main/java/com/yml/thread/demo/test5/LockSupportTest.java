package com.yml.thread.demo.test5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public void print() {
        System.out.println(Thread.currentThread().getName() + "\t");
    }

    static Thread t1 = null;

    static Thread t2 = null;
    static Thread t3 = null;

    public static void main(String[] args) throws InterruptedException {
        LockSupportTest test = new LockSupportTest();
        t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                test.print();
                LockSupport.unpark(t2);
                LockSupport.park();

            }

        }, "AA");
        t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                LockSupport.park();
                test.print();
                LockSupport.unpark(t1);


            }
        }, "BB");

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
