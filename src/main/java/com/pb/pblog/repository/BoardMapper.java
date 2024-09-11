package com.pb.pblog.repository;

import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface BoardMapper {
    //메인 페이지
    List<Board> mainBoard();


    //글 저장
    int boardSave(Board board);

    //글 수정
    int boardEdit(Board board);

    //글 삭제
    int boardDelete(Long bid);

    //카테고리 별 글 조회
    List<Board> boardSearch(@Param("category")String category,@Param("title")String title ,@Param("limit") Integer limit);

    //게시글 상세 조회
    Board boardDetail(Long bid);

    //유저가 작성한 글 조회
    List<Board> userWriteBoards(@Param("uid")Long uid);

}
