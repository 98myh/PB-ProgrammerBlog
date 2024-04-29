package com.pb.pblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //잠시 security 사용하지 않음
public class PBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PBlogApplication.class, args);
    }

}
