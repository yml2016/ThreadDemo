package com.yml.thread.demo.test5;

public class ShareResouce {

    private int num = 0;


    public ShareResouce(int num) {
        this.num = num;
    }


    public void call1() throws InterruptedException {

//        synchronized (this) {
//
//            while (num%2==0){
//                wait();
//            }
//            System.out.println(Thread.currentThread().getName() + "\t ");
//            num--;
//            notify();
//        }

        while (true) {
            //1.判断当前线程是否为可操作线程
            //2.枷锁之后进行操作
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "\t " + num);
                num--;
                //3.通知其他线程
                notifyAll();
                //4.判断是否可以结束，否则等待
                if (num <= 1) {
                    break;
                } else {
                    wait();
                }
            }
        }


    }

    public static void main(String[] args) {
        ShareResouce shareResouce = new ShareResouce(10);
        new Thread(() -> {
            try {

                shareResouce.call1();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA-1").start();

        new Thread(() -> {
            try {

                shareResouce.call1();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB-2").start();

    }


}
