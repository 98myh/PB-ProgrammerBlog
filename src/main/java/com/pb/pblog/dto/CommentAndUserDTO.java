package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//댓글 하나에 대한 DTO
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentAndUserDTO {
    private Long cid;
    private Long parent_cid;
    private String comment;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private Long bid;

    private Long uid;
    private String nickname;
}
