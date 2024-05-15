package com.pb.pblog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    //댓글 조회 테스트
    @Test
    public void boardCommentTest(){
        System.out.println("댓글 조회 테스트 : "+commentMapper.boardComment(28l));
    }
}