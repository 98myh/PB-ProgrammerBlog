package com.pb.pblog.repository;

import com.pb.pblog.entity.Board;
import com.pb.pblog.entity.Comment;
import com.pb.pblog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    //댓글 조회 테스트
    @Test
    public void boardCommentTest(){
        System.out.println("댓글 조회 테스트 : "+commentMapper.boardComment(36l));
    }

    //댓글 추가 테스트
    @Test
    public void commentInsertTest(){

        for(int i=0;i<5;i++){
            System.out.println("댓글 추가 "+i+" : "+commentMapper.commentInsert(Comment.builder()
                            .user(User.builder().uid(23l).build())
                            .board(Board.builder().bid(36l).build())
                            .comment("test"+i)
                            .create_date(LocalDateTime.now())
                            .update_date(LocalDateTime.now())
                    .build())
            );
        }
    }


    //댓글 삭제 테스트
    @Test
    public void commentDeleteTest(){
        System.out.println("댓글 삭제 테스트 1"+commentMapper.commentDelete(27l));
        System.out.println("댓글 삭제 테스트 2"+commentMapper.commentDelete(28l));
    }

    //댓글 수정 테스트
    @Test
    public void commentEditTest(){
        System.out.println("댓글 수정 테스트 1 "+commentMapper.commentEdit(Comment.builder()
                        .cid(24l)
                        .comment("zxcv")
                        .update_date(LocalDateTime.now())
                .build()));
    }
}