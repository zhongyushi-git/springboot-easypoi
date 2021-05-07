package com.example.easypoidemoadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.easypoidemoadmin.dao")
public class EasypoiDemoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasypoiDemoAdminApplication.class, args);
    }

}
