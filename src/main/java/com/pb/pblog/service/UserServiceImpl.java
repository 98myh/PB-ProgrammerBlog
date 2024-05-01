package com.pb.pblog.service;

import com.pb.pblog.dto.LoginRequestDTO;
import com.pb.pblog.dto.LoginResposeDTO;
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
    public LoginResposeDTO loginRequest(LoginRequestDTO loginRequestDTO) {
        LoginResposeDTO loginResponeDTO=userMapper.login(loginRequestDTO.getId(),loginRequestDTO.getPassword());
        if(loginResponeDTO==null){
            return null;
        }
        return loginResponeDTO;
    }

    @Override
    public int checkId(String id) {
        int check=userMapper.checkId(id);

        return check;
    }


    //    회원가입
    @Override
    public int signupRequest(SignupRequestDTO signupRequestDTO) {

        try {
            int signup=userMapper.signup(signupRequestDTO);
            return signup;
        }
        //예외 발생 -ex id 중복이거나 등등 -1 반환
        catch (Exception e){
            return -1;
        }
    }

}
