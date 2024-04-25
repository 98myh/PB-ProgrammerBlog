package com.mb.mblog.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class TestController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/test")
    public String test(){
        log.info("테스트");
        return "test";
    }
}
