package com.pb.pblog.service;

import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.MainResponseDTO;
import com.pb.pblog.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    //메인 테스트
    @Test
    void mainTest(){
        MainResponseDTO mainResponseDTO=boardService.main();

        System.out.println("최근 게시글 테스트 : "+mainResponseDTO.getListRecently());
        System.out.println("개발동향 게시글 테스트 : "+mainResponseDTO.getListTrend());
        System.out.println("개발스킬 게시글 테스트 : "+mainResponseDTO.getListSkill());
        System.out.println("알고리즘 게시글 테스트 : "+mainResponseDTO.getListAlgorithm());
    }

    @Test
    void imageSave() {
    }

    @Test
    void boardSave() {
    }

    @Test
    void boardSearch() {
        List<BoardAndUserDTO> boards=boardService.boardSearch("recently");
        for(int i=0;i<boards.toArray().length;i++){
            BoardAndUserDTO board=boards.get(i);
            System.out.println(board.getTitle());
        }
    }
}