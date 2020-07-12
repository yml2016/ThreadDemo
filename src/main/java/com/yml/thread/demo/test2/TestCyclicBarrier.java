package com.yml.thread.demo.test2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class TestCyclicBarrier {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        TaskTest2 taskTest = new TaskTest2(cyclicBarrier);
        Thread thread1 = new Thread(taskTest);
        Thread thread2 = new Thread(taskTest);
        Thread thread3 = new Thread(taskTest);

        thread1.start();
        thread2.start();
        Thread.sleep(10000);
        thread3.start();

        long endTime = System.currentTimeMillis();
        System.out.println("执行消耗时间:"+(endTime-startTime));

    }


}


class TaskTest2 implements Runnable{

    private CyclicBarrier cyclicBarrier;
    public TaskTest2(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行...");
        try {
            Thread.sleep(1000); //执行业务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"完成。");

    }
}