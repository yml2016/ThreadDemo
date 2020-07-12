package com.yml.thread.demo.test2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TTT {

    @PostConstruct
    public void init(){
        System.out.println("wo laizhixingggggggg");
    }
}
