package com.pb.pblog.service;

import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.dto.SignupDTO;
import com.pb.pblog.dto.UserInfoDTO;
import com.pb.pblog.entity.User;
import com.pb.pblog.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
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


    //회원가입
    @Override
    public int signupRequest(SignupRequestDTO signupRequestDTO) {

        //중복된 아이디 사용
        if(userMapper.checkId(signupRequestDTO.getId())!=0){
            return -1;
        }

        try {

            User user=User.builder()
                    .id(signupRequestDTO.getId())
                    .password(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()))
                    .nickname(signupRequestDTO.getNickname())
                    .create_date(LocalDateTime.now())
                    .update_date(LocalDateTime.now())
                    .role("user")
                    .build();

            int signup= userMapper.signup(user);
            return signup;
        }
        //예외 발생 - ex) id 중복이거나 등등 -1 반환
        catch (Exception e){
            return -1;
        }
    }

    //유저 정보 조회(간단)
    @Override
    public UserInfoDTO userInfo(Long uid) {
        try {
            User user = userMapper.userInfo(uid);
            return UserInfoDTO.builder()
                    .uid(user.getUid())
                    .nickname(user.getNickname())
                    .create_date(user.getCreate_date())
                    .build();
        }catch (Exception e){
            log.error(e);
            return null;
        }
    }
}
