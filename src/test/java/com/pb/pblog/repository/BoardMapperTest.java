package com.pb.pblog.repository;

import com.pb.pblog.dto.BoardSaveDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    //저장 테스트
    @Test
    @Transactional
    public void saveTest(){
        System.out.println("게시글 저장 테스트 : "+boardMapper.boardSave(BoardSaveDTO.builder()
                        .uid(21l)
                        .category("etc")
                        .content("test")
                        .title("test~")
                .build()));
    }
}