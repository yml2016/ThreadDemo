package com.yml.thread.demo.test4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){

        Thread currentThread = Thread.currentThread();
        while(!atomicReference.compareAndSet(null,currentThread)){

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"没有抢到锁，自旋中。。。");
        }
        System.out.println(Thread.currentThread().getName()+"\t 线程抢到锁了");
    }

    public void unLock(){
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(currentThread,null);
    }

    public static void main(String[] args) {
        SpinLock spinLock =new SpinLock();
        new Thread(() -> {

            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);//dobiz
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                spinLock.unLock();
            }

        },"AA").start();

        new Thread(() -> {
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(3);//dobiz
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                spinLock.unLock();
            }

        },"BB").start();
    }
}
