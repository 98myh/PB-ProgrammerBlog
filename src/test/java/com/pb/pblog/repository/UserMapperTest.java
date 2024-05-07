package com.pb.pblog.repository;

import com.pb.pblog.dto.SignupDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;


    //중복 테스트
    @Test
    public void checkTest(){
        System.out.println("중복 테스트 : "+userMapper.checkId("abcd"));
    }

    //회원가입 테스트
    @Test
    @Transactional
    public void signupTest(){
        System.out.println("회원가입 테스트 : "+userMapper.signup(SignupDTO.builder()
                        .id("abcd1")
                        .password("1234")
                        .nickname("abcd1")
                .build()));
    }
}