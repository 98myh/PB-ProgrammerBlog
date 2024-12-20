package com.pb.pblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ban {
    private long banid;             //정지 고유 아이디
    private User user;              //유저
    private String reason;          //정지 이유
    private LocalDateTime strDate;  //정지 시작일
    private LocalDateTime endDate;  //정지 종료일
}
