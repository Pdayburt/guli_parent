package com.guli.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.guli.edu.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSMApplication.class, args);
    }
}
