package com.wy.newblog;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableSwagger2Doc
@SpringBootApplication
public class NewblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewblogApplication.class, args);
    }
}
