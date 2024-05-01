package com.pb.pblog.repository;

import com.pb.pblog.dto.LoginResposeDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
//    로그인
    LoginResposeDTO login(String id, String password);

//    중복확인
    int checkId(String id);

//    회원가입
    int signup(SignupRequestDTO signupRequestDTO);

}
