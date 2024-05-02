package com.pb.pblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PBlogApplication.class, args);
    }

}
