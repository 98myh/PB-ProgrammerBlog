package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    //유저 고유 아이디
    private long uid;
    //아이디
    private String id;
    //비밀번호
    private String password;
    //닉네임
    private String nickname;
    //권한
    private String role;

    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
