package com.yml.thread.demo.test;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable {
    private MyRunnable() {
    }

    private static MyRunnable myRunnable;

    public static MyRunnable getInstance() {
        synchronized (MyRunnable.class) {
            if (myRunnable == null) {
                myRunnable = new MyRunnable();
            }
        }
        return myRunnable;
    }

    /*@Override
    public void run() {
        //Lock lock = new ReentrantLock();
        System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\t");
        synchronized (this) {
            for (int i = 0; i < 4; i++) {
                System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\tnum:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }*/
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\t");
        lock.lock();
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\tnum:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
