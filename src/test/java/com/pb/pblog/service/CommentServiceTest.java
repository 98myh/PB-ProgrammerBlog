package com.pb.pblog.service;

import com.pb.pblog.dto.CommentEditDTO;
import com.pb.pblog.dto.CommentSaveDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    //댓글 추가 테스트
    @Test
    @Transactional
    void commentInsert() {
        System.out.println(commentService.commentInsert(CommentSaveDTO.builder()
                        .comment("aaaa")
                        .bid(36l)
                .build()));
    }

    //댓글 삭제 테스트
    @Test
    @Transactional
    void commentDelete() {
        System.out.println(commentService.commentDelete(24l));
    }

    //댓글 수정 테스트
    @Test
    void commentEdit() {
        System.out.println(commentService.commentEdit(CommentEditDTO.builder()
                        .cid(24l)
                        .comment("ffffff")
                .build()));
    }
}