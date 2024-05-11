package com.pb.pblog.service;

import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.dto.UserDTO;
import com.pb.pblog.entity.User;

public interface UserService {
    //중복확인
    int checkId(String id);

    //회원가입
    int signupRequest(SignupRequestDTO signupRequestDTO);

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

