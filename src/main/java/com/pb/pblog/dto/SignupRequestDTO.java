package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {
//    아이디
    private String id;

//    비밀번호
    private String password;

//    비밀번호 재입력
    private String repassword;

//    닉네임
    private String nickname;
}
