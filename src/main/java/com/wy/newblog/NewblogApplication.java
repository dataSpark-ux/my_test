package com.wy.newblog;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wy
 */
@EnableSwagger2Doc
@SpringBootApplication
@EnableAsync
public class NewblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewblogApplication.class, args);
    }
}
