package com.pb.pblog.repository;

import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.entity.Board;
import jakarta.annotation.Nullable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //글 저장
    int boardSave(BoardSaveDTO boardSaveDTO);

    //카테고리 별 글 조회
    List<BoardAndUserDTO> boardSearch(String category, Integer limit);

}
