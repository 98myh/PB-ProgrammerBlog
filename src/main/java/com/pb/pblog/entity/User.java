package com.pb.pblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long uid; //유저 고유 아이디
    private String id; //아이디
    private String password; //비밀번호
    private String email; //이메일
    private String name; //이름
    private String nickname; //닉네임
    private String role; //권한
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
