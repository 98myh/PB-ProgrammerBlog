package com.pb.pblog.service;

import com.pb.pblog.dto.LoginRequestDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;


    //로그인 테스트
    @Test
    public void loginTest(){
        System.out.println("로그인 서비스 테스트 : "+userService.loginRequest(LoginRequestDTO.builder()
                        .id("abcd")
                        .password("1234")
                .build()));
        System.out.println("로그인 서비스 테스트2 : "+userService.loginRequest(LoginRequestDTO.builder()
                .id("abcd")
                .password("123456")
                .build()));
    }

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
                    .id("aaaaa")
                    .password("1234")
                    .nickname("aaaa")
                    .build()));

            System.out.println("회원가입 테스트 2: " + userService.signupRequest(SignupRequestDTO.builder()
                    .id("abcd")
                    .password("1234")
                    .nickname("aaaa")
                    .build()));
        }catch (Exception exception){
            System.out.println("예외 : "+exception);
        }
    }
}