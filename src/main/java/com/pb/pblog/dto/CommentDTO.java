package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//상위 댓글 + 하위 댓글 조회에 필요한 DTO
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private CommentAndUserDTO topComment; //상위댓글 하나
    private List<CommentAndUserDTO> childComment; //하위댓글 여러개
}
