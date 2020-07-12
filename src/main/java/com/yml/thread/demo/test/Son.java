package com.yml.thread.demo.test;

public class Son extends Father{
    private String i= test();
    private static String j= method1();

    static {
        System.out.println("(6)");
    }

    public Son(){
        System.out.println("(7)");
    }

    {
        System.out.println("(8)");
    }

    private static String method1() {
        System.out.println("(9)");
        return "i";
    }

    @Override
    public String test() {
        System.out.println("(10)");
        return "j";
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println();
        Son son1 = new Son();
    }
}
