package com.pb.pblog.service;

import com.pb.pblog.dto.LoginRequestDTO;
import com.pb.pblog.dto.UserDTO;
import com.pb.pblog.dto.UserDetailsDTO;
import com.pb.pblog.dto.SignupRequestDTO;
import com.pb.pblog.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    //로그인 데이터
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO=userMapper.userDetails(username);
        if(userDTO !=null){
            return new UserDetailsDTO(userDTO);
        }
        return null;
    }


    //아이디 중복 확인
    @Override
    public int checkId(String id) {
        int check=userMapper.checkId(id);

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
            //비밀번호 암호화
            signupRequestDTO.setPassword(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()));

            int signup=userMapper.signup(signupRequestDTO);
            return signup;
        }
        //예외 발생 -ex id 중복이거나 등등 -1 반환
        catch (Exception e){
            return -1;
        }
    }


}
