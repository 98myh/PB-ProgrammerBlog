package com.pb.pblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long uid; //유저 고유 아이디
    private Long bio; //게시판 고유 아이디
    private String title; //제목
    private String category; //카테고리
    private String content; //내용
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
