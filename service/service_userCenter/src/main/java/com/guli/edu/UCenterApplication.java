package com.guli.edu;

import com.guli.edu.entity.UCenterMember;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.guli.edu.mapper")
@SpringBootApplication
public class UCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class, args);
    }
}
