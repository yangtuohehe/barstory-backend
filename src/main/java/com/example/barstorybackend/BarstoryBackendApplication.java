package com.example.barstorybackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.barstorybackend.mapper")
public class BarstoryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarstoryBackendApplication.class, args);
    }

}
