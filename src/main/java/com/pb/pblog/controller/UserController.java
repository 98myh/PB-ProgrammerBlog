package com.pb.pblog.controller;

import com.pb.pblog.dto.*;
import com.pb.pblog.service.BoardService;
import com.pb.pblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
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
        }catch (Exception exception) {
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
    @PostMapping("/findId-check")
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
    @PostMapping("/findPwd-check")
    public String findPwdRequest(@ModelAttribute FindPwdDTO findPwdDTO, RedirectAttributes redirect,Model model){
        if(userService.findPwd(findPwdDTO)>0) {
            redirect.addFlashAttribute("id", findPwdDTO.getId());
            redirect.addFlashAttribute("text","새비밀번호를 설정해주세요.");
            return "redirect:/change-pwd";
        }else{
            model.addAttribute("text","정보를 다시 입력해주세요.");
            return "login/findPwd";
        }
    }

    //비밀번호 변경 페이지
    @GetMapping("/change-pwd")
    public String changePwd(){
        return "login/changePwd";
    }

    //비밀번호 변경
    @ResponseBody
    @PutMapping("/change-pwd-req")
    public ResponseEntity<?> changePwdReq(@RequestBody ChangePwdDTO changePwdDTO){

        if(userService.changePwd(changePwdDTO)>0){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
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

    //정보수정 페이지로 이동
    @GetMapping("/edit-user")
    public String userEditPage(){
        return "user/editUser";
    }

    //정보수정
    @ResponseBody
    @PutMapping("/edit-user-req")
    public ResponseEntity<?> editUser(@RequestBody EditUserDTO editUserDTO){
        if(userService.editUser(editUserDTO)>0){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }
}
