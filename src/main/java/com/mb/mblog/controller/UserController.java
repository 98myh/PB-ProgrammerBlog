package com.mb.mblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    //로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage(){
        return "/login/login";
    }

    //로그인 요청
    @PostMapping("/login")
    public String login(){
        return "/login/loginCheck";
    }


}
