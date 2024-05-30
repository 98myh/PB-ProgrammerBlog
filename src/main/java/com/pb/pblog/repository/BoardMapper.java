package com.pb.pblog.repository;

import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //메인 페이지
    List<Board> mainBoard();


    //글 저장
    int boardSave(Board board);

    //카테고리 별 글 조회
    List<Board> boardSearch(String category,String title ,Integer limit);


    //게시글 상세 조회
    Board boardDetail(Long bid);

}
