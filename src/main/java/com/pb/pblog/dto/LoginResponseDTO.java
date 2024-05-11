package com.pb.pblog.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    //아이디
    private String id;
    //비밀번호
    private String password;
    //닉네임
    private String nickname;
    //권한
    private String role;
}
