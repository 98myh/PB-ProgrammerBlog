package com.pb.pblog.repository;

import com.pb.pblog.dto.SignupDTO;
import com.pb.pblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

//    중복확인
    int checkId(String id);

//    유저 정보 조회(디테일)
    User userDetails(String id);

//    회원가입
    int signup(User user);

//    유저 정보(간단)
    User userInfo(Long uid);

}
