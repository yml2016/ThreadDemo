package com.yml.thread.demo.test5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ShareResouce2 {

    private int num = 0;


    public ShareResouce2(int num) {
        this.num = num;
    }


    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void call1() {
        lock.lock();
        try {

            while (num % 2 == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call2() {

        lock.lock();
        try {

            while (num % 2 != 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ShareResouce2 shareResouce = new ShareResouce2(10);
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResouce.call1();
            }

        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResouce.call2();
            }

        }, "BB").start();
    }
}
