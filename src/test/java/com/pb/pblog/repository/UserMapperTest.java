package com.pb.pblog.repository;

import com.pb.pblog.dto.SignupDTO;
import com.pb.pblog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    //비밀번호 암호화
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;




    //중복 테스트
    @Test
    public void checkTest(){
        System.out.println("중복 테스트 : "+ userMapper.checkId("abcd"));
    }

    //회원가입 테스트
    @Test
    @Transactional
    public void signupTest(){
        System.out.println("회원가입 테스트 : "+ userMapper.signup(User.builder()
                        .id("abcd1")
                        .password("1234")
                        .nickname("abcd1")
                .build()));
    }


    //유저 정보 조회(간단) 테스트
    @Test
    public void userInfoTest(){
        System.out.println(userMapper.userInfo(23l));
    }

    //아이디 찾기

    //비밀번호 찾기
    @Test
    public void findPwdTest(){
        System.out.println(userMapper.findPwd(User.builder()
                        .id("test")
                        .name("test")
                        .email("test@naver.com")
                .build()));
    }

    //비밀번호 변경 테스트
    @Test
    public void changePwdTest(){
        System.out.println(userMapper.changePwd(User.builder()
                        .id("test")
                        .password(bCryptPasswordEncoder.encode("test"))
                .build()));
    }

    //유저 정보 수정 테스트
    @Test
    @Transactional
    public void editUserTest(){
        System.out.println(userMapper.editUser(User.builder()
                        .uid(23l)
                        .password(bCryptPasswordEncoder.encode("test"))
                        .nickname("테스트")
                .build()));
    }

    //유저 탈퇴 테스트
    @Test
    @Transactional
    public void deleteUser(){
        System.out.println(userMapper.deleteUser(23l));
    }

    //유저 확인 테스트
    @Test
    public void confirmUserTest(){
        String password=userMapper.confirmUser(23l);
        System.out.println(bCryptPasswordEncoder.matches("test",password));
    }
}