package com.suncd.conn.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@MapperScan("com.suncd.conn.manager.dao")
@SpringBootApplication
public class ConnManagerBoot {

//    @PostConstruct
//    void setDefaultTimezone() {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//    }

    public static void main(String[] args) {
        SpringApplication.run(ConnManagerBoot.class, args);
    }
}
