package com.pb.pblog.service;

import com.pb.pblog.dto.*;
import com.pb.pblog.entity.User;

public interface UserService {
    //중복확인
    int checkId(String id);

    //회원가입
    int signupRequest(SignupRequestDTO signupRequestDTO);

    //아이디 찾기
    IdDTO findId(FindIdDTO findIdDTO);

    //비밀번호 찾기
    int findPwd(FindPwdDTO findPwdDTO);

    //비밀번호 변경
    int changePwd(ChangePwdDTO changePwdDTO);

    //유저 정보조회(간단)
    UserInfoDTO userInfo(Long uid);

    //DTO -> Entity 변환
    default User userDTOtoEntity(UserDTO userDTO){
        return User.builder()
                .uid(userDTO.getUid())
                .id(userDTO.getId())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .create_date(userDTO.getCreate_date())
                .update_date(userDTO.getUpdate_date())
                .build();
    }
}

