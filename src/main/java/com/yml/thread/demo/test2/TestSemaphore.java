package com.yml.thread.demo.test2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class TestSemaphore {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Semaphore semaphore = new Semaphore(10);
        TaskTest1 taskTest = new TaskTest1(semaphore,countDownLatch);
        Thread thread1 = new Thread(taskTest);
        Thread thread2 = new Thread(taskTest);
        Thread thread3 = new Thread(taskTest);

        thread1.start();
        thread2.start();
        thread3.start();
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("执行消耗时间:"+(endTime-startTime));

    }


}


class TaskTest1 implements Runnable{

    private Semaphore semaphore;
    private CountDownLatch countDownLatch;
    public TaskTest1(Semaphore semaphore,CountDownLatch countDownLatch){
        this.semaphore = semaphore;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
            semaphore.acquire(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000); //执行业务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行");
        semaphore.release(5);
        countDownLatch.countDown();
    }
}