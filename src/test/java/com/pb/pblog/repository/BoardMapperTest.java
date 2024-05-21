package com.pb.pblog.repository;

import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.entity.Board;
import com.pb.pblog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    //메인 테스트
    @Test
    public void mainListTest(){
        List<Board> boards=boardMapper.mainBoard();
        System.out.println("메인 테스트");
        int i=0;
        for(Board board:boards){
            System.out.println(++i+" "+board);
        }
    }

    //저장 테스트
    @Test
    @Transactional
    public void saveTest(){
        System.out.println("게시글 저장 테스트 : "+boardMapper.boardSave(Board.builder()
                        .user(User.builder()
                                .uid(21l)
                                .build())
                        .category("etc")
                        .content("test")
                        .title("test~")
                .build()));
    }

    //글 조회 테스트
    @Test
    public void boardSearchTest(){
        System.out.println("글 조회 테스트 : "+boardMapper.boardSearch("recently",null));
    }

    //게시글 상세 조회 테스트
    @Test
    public void boardDetailTest(){
        System.out.println("게시글 상세 조회 테스트 : "+boardMapper.boardDetail(29l));
    }

    //댓글 조회 테스트

}
