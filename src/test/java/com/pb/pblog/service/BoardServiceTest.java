package com.pb.pblog.service;

import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.BoardEditDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.dto.MainResponseDTO;
import com.pb.pblog.entity.Board;
import com.pb.pblog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional
    void boardSave() {
        System.out.println(
        boardService.boardSave(BoardSaveDTO.builder()
                        .uid(3l)
                        .category("etc")
                        .content("test")
                        .title("test")
                .build())
        );
    }

    //게시글 조회
    @Test
    void boardSearch() {
        List<BoardAndUserDTO> boards=boardService.boardSearch("recently","test");
        for(int i=0;i<boards.toArray().length;i++){
            BoardAndUserDTO board=boards.get(i);
            System.out.println(board.getTitle());
        }
    }

    //상세조회 테스트
    @Test
    void boardDetailTest(){
        System.out.println("상세조회 테스트 : "+boardService.boardDetails(31l));
    }

    //수정 테스트
    @Test
    void boardEditTest() {
        System.out.println(boardService.boardEdit(BoardEditDTO.builder()
                        .bid(33l)
                        .title("dddd")
                        .category("etc")
                        .content("zzzzzzz")
                .build()));
    }

    //유저가 작성한 글 테스트
    @Test
    void userWriteBoardTest(){
        System.out.println(boardService.userWriteBoards(23l));
    }
}

