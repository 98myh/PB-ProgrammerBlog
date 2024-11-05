package com.pb.pblog.service;

import com.pb.pblog.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    //중복확인 테스트
    @Test
    public void checkIdTest(){
        System.out.println("중복 확인 테스트 : "+userService.checkId("abcd"));
        System.out.println("중복 확인 테스트2 : "+userService.checkId("abcdef"));
    }

    //회원가입 테스트
    @Test
    public void signupTest() {
        try {
            System.out.println("회원가입 테스트 : " + userService.signupRequest(SignupRequestDTO.builder()
                            .id("qqqq")
                            .password("1234")
                            .repassword("1234")
                            .nickname("abcd")
                    .build()));

            System.out.println("회원가입 테스트 2: " + userService.signupRequest(SignupRequestDTO.builder()
                    .id("wwww")
                    .password("1234")
                    .repassword("1234")
                    .nickname("abcd2")
                    .build()));

        }catch (Exception exception){
            System.out.println("예외 : "+exception);
        }
    }

    //유저 정보 조회(간단) 테스트
    @Test
    public void userInfoTest(){
        System.out.println(userService.userInfo(23l));
    }

    //아이디 찾기
    @Test
    public void findIdTest(){
        System.out.println(userService.findId(FindIdDTO.builder()
                        .name("test")
                        .email("test@naver.com")
                .build()));
    }

    //비밀번호 찾기
    @Test
    public void findPwdTest(){
        System.out.println(userService.findPwd(FindPwdDTO.builder()
                        .id("test")
                        .name("test")
                        .email("test@naver.com")
                .build()));
    }

    //비밀번호 변경
    @Test
    @Transactional
    public void changePwdTest(){
        System.out.println(userService.changePwd(ChangePwdDTO.builder()
                        .id("test")
                        .password("asdf")
                        .repassword("asdf")
                .build()));
    }

    //회원 정보 수정 테스트
    @Test
    @Transactional
    public void editUserTest(){
        System.out.println(userService.editUser(EditUserDTO.builder()
                        .uid(23l)
                        .oldPassword("test")
                        .newPassword("")
                        .nickname("test")
                .build()));
    }


    //회원 탈퇴
    @Test
    @Transactional
    public void deleteUserTest(){
        System.out.println(userService.deleteUser(UidDTO.builder()
                        .uid(23l)
                .build()));
    }
}