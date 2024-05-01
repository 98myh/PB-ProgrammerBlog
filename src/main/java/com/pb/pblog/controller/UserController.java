package com.pb.pblog.controller;

import com.pb.pblog.dto.IdDTO;
import com.pb.pblog.dto.LoginRequestDTO;
import com.pb.pblog.dto.LoginResposeDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage(HttpSession httpSession){
        //로그인 한 경우 login페이지로 이동못하게 main페이지로 이동
        if(httpSession.getAttribute("user")!=null){
            return "redirect:/";
        }
        return "/login/login";
    }

    //로그인 요청
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDTO loginRequestDTO, Model model, HttpSession httpSession){
        System.out.println("요청 들어옴"+loginRequestDTO.getId());
        LoginResposeDTO loginResposeDTO=userService.loginRequest(loginRequestDTO);
        if(loginResposeDTO!=null){
            httpSession.setAttribute("user",loginResposeDTO.getId());
            httpSession.setAttribute("role",loginResposeDTO.getRole());
            httpSession.setAttribute("nickname",loginResposeDTO.getNickname());
            return "redirect:/";
        }
        model.addAttribute("login false","로그인 실패");
        return "/login/login";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate(); //session 초기화
        return "redirect:/login";
    }


    //회원가입 페이지 이동
    @GetMapping("/signup")
    public String signupPage(@ModelAttribute SignupRequestDTO signupRequestDTO){return "/login/signup";}

    //중복확인
    @PostMapping("/check-id")
    public int checkId(@RequestBody IdDTO idDTO){

        return userService.checkId(idDTO.getId());
    }

    //회원가입 요청
    @PostMapping("/signup")
    public int signupRequest(@ModelAttribute SignupRequestDTO signupRequestDTO){
        return userService.signupRequest(signupRequestDTO);
    }

}
