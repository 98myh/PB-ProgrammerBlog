package com.pb.pblog.controller;

import com.pb.pblog.dto.*;
import com.pb.pblog.service.BoardService;
import com.pb.pblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BoardService boardService;

    //로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage(){
        //로그인 한 경우 login페이지로 이동못하게 main페이지로 이동
        String id= SecurityContextHolder.getContext().getAuthentication().getName();

        if(id!="anonymousUser"){
            return "redirect:/";
        }
        return "login/login";
    }

    //중복확인
    @PostMapping("/check-id")
    public ResponseEntity<Integer> checkId(@ModelAttribute IdDTO idDTO){
        return new ResponseEntity<>(userService.checkId(idDTO.getId()),HttpStatus.OK);
    }


    //회원가입 페이지 이동
    @GetMapping("/signup")
    public String signupPage(){
        String id= SecurityContextHolder.getContext().getAuthentication().getName();

        if(id!="anonymousUser"){
            return "redirect:/";
        }
        return "login/signup";
    }


    //회원가입 요청
    @PostMapping("/signupProc")
    public String signupRequest(@ModelAttribute SignupRequestDTO signupRequestDTO){
        try {
            userService.signupRequest(signupRequestDTO);
            return "redirect:/login";
        }catch (Exception exception){
            return "login/signup";
        }
    }

    //아이디 찾기 페이지 이동
    @GetMapping("/find-id")
    public String findIdPage(){
        String id= SecurityContextHolder.getContext().getAuthentication().getName();

        if(id!="anonymousUser"){
            return "redirect:/";
        }
        return "login/findId";
    }

    //아이디 찾기 요청
    @GetMapping("/findId-check")
    public String findIdReqeust(@ModelAttribute FindIdDTO findIdDTO, Model model){
        IdDTO idDTO=userService.findId(findIdDTO);
        model.addAttribute("id",idDTO);
        //해당 아이디가 있으면
        if(idDTO!=null){
            model.addAttribute("id",idDTO);
            return "login/login";
        }
        //없으면
        else {
            return "login/findId";
        }
    }

    //비밀번호 찾기 페이지 이동
    @GetMapping("/find-pwd")
    public String findPwdPage(){
        String id= SecurityContextHolder.getContext().getAuthentication().getName();

        if(id!="anonymousUser"){
            return "redirect:/";
        }

        return "login/findPwd";
    }

    //비밀번호 찾기 요청
    @GetMapping("/findPwd-check")
    public String findPwdRequest(@ModelAttribute FindPwdDTO findPwdDTO,Model model){

        return "/login/findPwd";
    }

    //비밀번호 변경
    @PutMapping("change-pwd")
    public ResponseEntity<?> changePwd(){


        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    //마이페이지 이동
    @GetMapping("/mypage/{uid}")
    public  String myPage(@PathVariable(value = "uid")Long uid, Model model){
        //유저 닉네임, 가입일자
        UserInfoDTO userInfoDTO=userService.userInfo(uid);
        model.addAttribute("userInfo",userInfoDTO);
        //유저가 작성한 글들
        List<BoardAndUserDTO> boardAndUserDTOS=boardService.userWriteBoards(uid);
        model.addAttribute("boards",boardAndUserDTOS);
        return "user/mypage";
    }
}
