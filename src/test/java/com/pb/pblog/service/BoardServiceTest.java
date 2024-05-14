package com.pb.pblog.service;

import com.pb.pblog.dto.BoardAndUserDTO;
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