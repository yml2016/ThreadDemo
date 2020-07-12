package com.yml.thread.demo.test5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ShareResouce3 {

    private int num = 1;


    public ShareResouce3(int num) {
        this.num = num;
    }
    public ShareResouce3() {
    }


    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void call1() {
        lock.lock();
        try {

            while (num!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            num=2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call2() {

        lock.lock();
        try {
            while (num!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            num=3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call3() {
        lock.lock();
        try {
            while (num!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            num=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ShareResouce3 shareResouce = new ShareResouce3();
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
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResouce.call3();
            }

        }, "CC").start();

    }
}
