package com.yml.thread.demo.test2;

public class MyTask implements Runnable {

    int num = 99;  //1,2,3
    int count = 0;
    @Override
    public void run() {

        while (true) {
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            synchronized (this) {
                notifyAll();
                count++;
                //判断这个线程是否要执行
                int s = num % 3;
                int c = Integer.valueOf(Thread.currentThread().getName().substring(0,1));
                if ((c == s || (s == 0&&c==3))) {
                    if (num > 0) {
                        System.out.println(Thread.currentThread().getName() + "->" + num);
                        num--;
                    }

                }
                if (num==0){
                    System.out.println(Thread.currentThread().getName() + "->结束，执行次数"+count);
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}

class test {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread1 = new Thread(myTask);
        thread1.setName("1线程1111");
        Thread thread2 = new Thread(myTask);
        thread2.setName("2线程2222");
        Thread thread3 = new Thread(myTask);
        thread3.setName("3线程3333");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}