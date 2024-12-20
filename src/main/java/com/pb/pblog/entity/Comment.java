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
public class Comment {
    private Board board;                //게시판 고유 아이디
    private User user;                  //유저 고유 아이디
    private Long cid;                   //댓글 고유 아이디
    private Long parent_cid;            //부모 댓글 고유 아이디
    private String comment;             //댓글
    private LocalDateTime create_date;
    private LocalDateTime update_date;


}
