package com.guli.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@MapperScan("com.guli.edu.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class CMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(CMSApplication.class,args);
    }
}
