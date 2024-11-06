package com.pb.pblog.service;

import com.pb.pblog.dto.*;
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
                    .name(signupRequestDTO.getName())
                    .email(signupRequestDTO.getEmail())
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
            log.error(e);
            return -1;
        }
    }

    //아이디 찾기
    @Override
    public IdDTO findId(FindIdDTO findIdDTO) {
        try {
            User user = userMapper.findId(User.builder()
                    .email(findIdDTO.getEmail())
                    .name(findIdDTO.getName())
                    .build());
            IdDTO idDTO = IdDTO.builder()
                    .id(user.getId())
                    .build();
            return idDTO;
        }
        catch (Exception e){
            log.error(e);
            return null;
        }
    }

    //비밀번호 찾기
    @Override
    public int findPwd(FindPwdDTO findPwdDTO) {
        try{
            int result=userMapper.findPwd(User.builder()
                            .id(findPwdDTO.getId())
                            .name(findPwdDTO.getName())
                            .email(findPwdDTO.getEmail())
                    .build());
            return result;
        }catch (Exception e){
            log.error(e);
            return -1;
        }
    }

    //비밀번호 변경
    @Override
    public int changePwd(ChangePwdDTO changePwdDTO) {
        try{
            if (!changePwdDTO.getPassword().equals(changePwdDTO.getRepassword())){
                return -2;
            }
            int result=userMapper.changePwd(User.builder()
                            .id(changePwdDTO.getId())
                            .password(bCryptPasswordEncoder.encode(changePwdDTO.getPassword()))
                    .build());
            return result;
        }catch (Exception e){
            log.error(e);
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

    //회원 정보 수정
    @Override
    public int editUser(EditUserDTO editUserDTO) {
        try{
            String password=userMapper.confirmUser(editUserDTO.getUid());
            //비밀번호가 맞다면 회원 정보 수정
            if (bCryptPasswordEncoder.matches(editUserDTO.getOldPassword(), password)){
                String newPassword=editUserDTO.getNewPassword()==""? editUserDTO.getOldPassword() : editUserDTO.getNewPassword();
                log.error(newPassword);
                User user=User.builder()
                    .uid(editUserDTO.getUid())
                    .password(bCryptPasswordEncoder.encode(newPassword))
                    .nickname(editUserDTO.getNickname())
                    .build();

            return userMapper.editUser(user);}
            else{
                log.error("비밀번호 오류");
                return -2;
            }
        }catch(Exception e){
            log.error(e);
            return -1;
        }
    }

    //회원 탈퇴
    @Override
    public int deleteUser(UserDeleteDTO userDeleteDTO) {
        try{
            String password=userMapper.confirmUser(userDeleteDTO.getUid());
            //비밀번호가 같으면 삭제
            if (bCryptPasswordEncoder.matches(userDeleteDTO.getPassword(), password)) {
                return userMapper.deleteUser(userDeleteDTO.getUid());
            }else{
                log.error("비밀번호 틀림");
                return -2;
            }
        }catch (Exception e){
            log.error(e);
            return -1;
        }
    }
}
