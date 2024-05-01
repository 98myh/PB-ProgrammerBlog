package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResposeDTO {
    //아이디
    private String id;
    //역할
    private String role;
    //닉네임
    private String nickname;
}
