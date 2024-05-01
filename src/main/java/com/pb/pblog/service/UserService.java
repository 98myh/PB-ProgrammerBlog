package com.pb.pblog.service;

import com.pb.pblog.dto.LoginRequestDTO;
import com.pb.pblog.dto.LoginResposeDTO;
import com.pb.pblog.dto.SignupRequestDTO;

public interface UserService {
    //로그인
    LoginResposeDTO loginRequest(LoginRequestDTO loginRequestDTO);

    //중복확인
    int checkId(String id);

    //회원가입
    int signupRequest(SignupRequestDTO signupRequestDTO);
}

