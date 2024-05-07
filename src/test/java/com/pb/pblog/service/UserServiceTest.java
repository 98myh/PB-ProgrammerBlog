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
}