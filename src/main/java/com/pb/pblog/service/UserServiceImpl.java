package com.pb.pblog.service;

import com.pb.pblog.dto.LoginRequestDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

//    로그인
    @Override
    public int loginRequest(LoginRequestDTO loginRequestDTO) {
        int login=userMapper.login(loginRequestDTO.getId(),loginRequestDTO.getPassword());
        if(login==0){
            return 0;
        }
        return 1;
    }

    @Override
    public int checkId(String id) {
        int check=userMapper.checkId(id);
        //check가 있는경우 0리턴
        if(check!=0){
            return 0;
        }
        return 1;
    }


    //    회원가입
    @Override
    public int signupRequest(SignupRequestDTO signupRequestDTO) {
        int signup=userMapper.signup(signupRequestDTO);
        if(signup==0){
            return 0;
        }
        return 1;
    }

}
