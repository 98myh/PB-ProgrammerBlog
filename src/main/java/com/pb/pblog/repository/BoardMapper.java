package com.pb.pblog.repository;

import com.pb.pblog.dto.BoardSaveDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    //글 저장
    int boardSave(BoardSaveDTO boardSaveDTO);

}
