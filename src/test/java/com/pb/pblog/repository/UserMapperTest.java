package com.pb.pblog.repository;

import com.pb.pblog.dto.SignupRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    //로그인 테스트
    @Test
    public void loginTest(){
        System.out.println("로그인 테스트 : "+userMapper.login("abcd","zxcv"));
    }

    //중복 테스트
    @Test
    public void checkTest(){
        System.out.println("중복 테스트 : "+userMapper.checkId("abcd"));
    }

    //회원가입 테스트
    @Test
    @Transactional
    public void signupTest(){
        System.out.println("회원가입 테스트 : "+userMapper.signup(SignupRequestDTO.builder()
                        .id("abcde")
                        .password("12345")
                        .nickname("test")
                .build()));
    }
}