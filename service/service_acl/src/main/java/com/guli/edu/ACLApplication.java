package com.guli.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.guli.edu.mapper")
public class ACLApplication {
    public static void main(String[] args) {
        SpringApplication.run(ACLApplication.class, args);
    }
}
