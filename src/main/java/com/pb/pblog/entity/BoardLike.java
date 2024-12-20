package com.pb.pblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardLike {
    private Board board;    //게시판 고유 아이디
    private User user;      //유저 고유 아이디
}
