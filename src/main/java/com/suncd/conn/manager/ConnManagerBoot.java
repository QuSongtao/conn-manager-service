package com.suncd.conn.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.suncd.conn.manager.dao")
@SpringBootApplication
public class ConnManagerBoot {

    public static void main(String[] args) {
        SpringApplication.run(ConnManagerBoot.class, args);
    }
}
