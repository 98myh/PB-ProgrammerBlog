package com.pb.pblog.controller;

import com.pb.pblog.dto.IdDTO;
import com.pb.pblog.dto.LoginRequestDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage(){
        return "/login/login";
    }

    //로그인 요청
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession httpSession){

        if(userService.loginRequest(loginRequestDTO)==1){
            return "redirect:/";
        }

        return "/login/login";
    }


    //회원가입 페이지 이동
    @GetMapping("/signup")
    public String signupPage(SignupRequestDTO signupRequestDTO){return "/login/signup";}

    //중복확인
    @PostMapping("/check-id")
    public int checkId(@RequestBody IdDTO idDTO){

        return userService.checkId(idDTO.getId());
    }

    //회원가입 요청
    @PostMapping("/signup")
    public int signupRequest(SignupRequestDTO signupRequestDTO){
        return userService.signupRequest(signupRequestDTO);
    }

}
