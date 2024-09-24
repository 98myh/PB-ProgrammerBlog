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

//    아이디 찾기
    User findId(User user);

//    비밀번호 찾기
    int findPwd(User user);

//    비밀번호 변경
    int changePwd(User user);

//    유저 정보(간단)
    User userInfo(Long uid);

//    유저 정보 수정
    int editUser(User user);

//    회원 탈퇴
    int deleteUser(Long uid);
}
