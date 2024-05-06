package com.pb.pblog.repository;

import com.pb.pblog.dto.UserDTO;
import com.pb.pblog.dto.UserDetailsDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

//    중복확인
    int checkId(String id);

//    유저 정보 조회
    UserDTO userDetails(String id);

//    회원가입
    int signup(SignupRequestDTO signupRequestDTO);

}
