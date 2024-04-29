package com.pb.pblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    //메인 페이지 이동
    @GetMapping("/")
    public String main(){
        return "/main/main";
    }
}
