package com.pb.pblog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class BoardController {

    //메인 페이지 이동
    @GetMapping("/")
    public String main(Model model){

        //서비스 단에 작성해주어야함
        //아이디 조회
//        String id=SecurityContextHolder.getContext().getAuthentication().getName();
//
//        //role 조회
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        Collection<?extends GrantedAuthority> authorities=authentication.getAuthorities();
//        Iterator<?extends GrantedAuthority> iter=authorities.iterator();
//        GrantedAuthority auth=iter.next();
//
//        String role=auth.getAuthority();



//        model.addAttribute("id",);
//        model.addAttribute("nickname",);
//        model.addAttribute("role",);
        return "main/main";
    }
}
