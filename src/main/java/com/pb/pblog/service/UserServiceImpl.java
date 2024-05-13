package com.pb.pblog.service;

import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.dto.SignupDTO;
import com.pb.pblog.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    //유저 db 접근
    private final UserMapper userMapper;

    //비밀번호 암호화
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    //아이디 중복 확인
    @Override
    public int checkId(String id) {
        int check= userMapper.checkId(id);
        return check;
    }


    //    회원가입
    @Override
    public int signupRequest(SignupRequestDTO signupRequestDTO) {

        //중복된 아이디 사용
        if(userMapper.checkId(signupRequestDTO.getId())!=0){
            return -1;
        }

        try {

            //회원가입에 필요한 DTO로 변환
            SignupDTO singupDTO= SignupDTO.builder()
                    .id(signupRequestDTO.getId())
                    .password(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()))
                    .nickname(signupRequestDTO.getNickname())
                    .build();

            int signup= userMapper.signup(singupDTO);
            return signup;
        }
        //예외 발생 - ex) id 중복이거나 등등 -1 반환
        catch (Exception e){
            return -1;
        }
    }
}
