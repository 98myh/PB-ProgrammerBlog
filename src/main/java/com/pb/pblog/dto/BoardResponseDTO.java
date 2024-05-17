package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDTO {
    private BoardAndUserDTO board; //게시글 + 작성자
    private List<CommentAndUserDTO> comments; //댓글들
}
