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
public class MainResponseDTO {
    //최근 게시글
    List<BoardAndUserDTO> listRecently;
    //개발동향
    List<BoardAndUserDTO> listTrend;
    //개발스킬
    List<BoardAndUserDTO> listSkill;
    //알고리즘
    List<BoardAndUserDTO> listAlgorithm;
}
