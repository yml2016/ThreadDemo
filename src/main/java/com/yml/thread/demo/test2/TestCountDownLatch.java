package com.yml.thread.demo.test2;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(5);
        TaskTest taskTest = new TaskTest(countDownLatch);
        Thread thread1 = new Thread(taskTest);
        Thread thread2 = new Thread(taskTest);
        Thread thread3 = new Thread(taskTest);
        Thread thread4 = new Thread(taskTest);
        Thread thread5 = new Thread(taskTest);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("执行消耗时间:"+(endTime-startTime));
        System.out.println(countDownLatch.getCount());

        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());
    }


}


class TaskTest implements Runnable{

    private CountDownLatch countDownLatch;
    public TaskTest(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000); //执行业务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行");
        countDownLatch.countDown();
    }
}