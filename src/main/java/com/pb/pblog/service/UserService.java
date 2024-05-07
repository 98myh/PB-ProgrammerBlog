package com.pb.pblog.service;

import com.pb.pblog.dto.SignupRequestDTO;

public interface UserService {
    //중복확인
    int checkId(String id);

    //회원가입
    int signupRequest(SignupRequestDTO signupRequestDTO);
}

