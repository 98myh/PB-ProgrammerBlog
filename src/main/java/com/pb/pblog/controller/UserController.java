package com.pb.pblog.controller;

import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.IdDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.dto.UserInfoDTO;
import com.pb.pblog.service.BoardService;
import com.pb.pblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String signupPage(){return "login/signup";}


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
