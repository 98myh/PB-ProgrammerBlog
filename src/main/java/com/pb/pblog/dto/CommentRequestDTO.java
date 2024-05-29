package com.pb.pblog.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private Long bid; //게시글 번호
    private Long parent_cid; //상위 댓글 번호
    private String comment; //댓글 내용
}
