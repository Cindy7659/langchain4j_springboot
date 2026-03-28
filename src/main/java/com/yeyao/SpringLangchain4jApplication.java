package com.yeyao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yeyao.mapper", annotationClass = Mapper.class)
public class SpringLangchain4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLangchain4jApplication.class, args);
    }

}
