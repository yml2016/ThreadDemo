package com.yml.thread.demo.test5;

import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ShareResouce4 {

    //启动标识
    private volatile boolean FLAG = true;

    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> queue = null;


    public ShareResouce4(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void producer() throws InterruptedException {
        String pro;
        boolean isSucc=false;
        while (FLAG){
            pro = atomicInteger.incrementAndGet()+"";
            isSucc= queue.offer(pro,2L, TimeUnit.SECONDS);
            if (isSucc){
                System.out.println(Thread.currentThread().getName()+"\t 生产"+pro+"成功！！！");
            }
            else {
                System.out.println(Thread.currentThread().getName()+"\t 生产"+pro+"失败！！！");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 生产结束！！！！");
    }


    public void consumer() throws InterruptedException {
        String res=null;
        while (FLAG){
            res = queue.poll(2L,TimeUnit.SECONDS);
            if (StringUtils.isEmpty(res)) {
                System.out.println(Thread.currentThread().getName()+"\t 消费失败！！！！");
            }
            else
               System.out.println(Thread.currentThread().getName()+"\t消费"+res);
        }
        System.out.println(Thread.currentThread().getName()+"\t 消费结束！！！！");
    }

    public void stop(){
        FLAG = false;
    }

    public static void main(String[] args) throws InterruptedException {
        ShareResouce4 shareResouce = new ShareResouce4(new SynchronousQueue<>());

        new Thread(() -> {
            try {
                shareResouce.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                shareResouce.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
//        new Thread(() -> {
//            try {
//                shareResouce.consumer();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"CC").start();
//        new Thread(() -> {
//            try {
//                shareResouce.producer();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"DD").start();


        TimeUnit.SECONDS.sleep(10);
        shareResouce.stop();
    }
}
