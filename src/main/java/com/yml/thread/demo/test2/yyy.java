package com.yml.thread.demo.test2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class yyy {
    @Bean
    public MyTask myTask(){
        System.out.println("hahhah");
        return new MyTask();
    }
}
