package com.example.easylocktest;

import com.easy.lock.annotation.EnableEasyLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEasyLock
@SpringBootApplication
public class EasyLockTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyLockTestApplication.class, args);
    }

}
