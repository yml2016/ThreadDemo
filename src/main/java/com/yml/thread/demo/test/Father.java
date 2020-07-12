package com.yml.thread.demo.test;

public class Father {
    private String i= test();
    private static String j= method1();

    static {
        System.out.println("(1)");
    }

    public Father(){
        System.out.println("(2)");
    }

    {
        System.out.println("(3)");
    }

    private static String method1() {
        System.out.println("(4)");
        return "i";
    }

    public String test() {
        System.out.println("(5)");
        return "j";
    }
}
