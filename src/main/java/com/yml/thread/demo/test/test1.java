package com.yml.thread.demo.test;


import lombok.Builder;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class test1 {


    public static void main(String[] args) {
        new Thread(MyRunnable.getInstance(), "Thread-A").start();
        new Thread(MyRunnable.getInstance(), "Thread-B").start();

        val lst = new ArrayList<book>();
        lst.add(new book());
        lst.add(new book());
        List lst1 = new ArrayList<book>();

    }
}
