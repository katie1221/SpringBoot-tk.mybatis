package com.example.gradledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.gradledemo.dao")
public class GradleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradleDemoApplication.class, args);
    }

}
