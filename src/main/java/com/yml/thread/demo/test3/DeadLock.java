package com.yml.thread.demo.test3;

import java.util.concurrent.TimeUnit;

public class DeadLock {

    Object monitor1 = new Object();
    Object monitor2 = new Object();

    private void call1() throws InterruptedException {
        synchronized (monitor1){
            System.out.println("wo shi call1");
            TimeUnit.SECONDS.sleep(1);
            synchronized (monitor2){
                System.out.println("wo shi call12");
            }
        }

    }

    private void call2() throws InterruptedException {
        synchronized (monitor2){
            System.out.println("wo shi call2");
            TimeUnit.MILLISECONDS.sleep(200);
            synchronized (monitor1){
                System.out.println("wo shi call21");
            }
        }

    }


    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();

        new Thread(() -> {
            try {
                deadLock.call1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                deadLock.call2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();

    }
}
