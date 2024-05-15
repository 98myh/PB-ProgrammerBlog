package com.pb.pblog.repository;

import com.pb.pblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    //게시글 댓글 조회
    List<Comment> boardComment(Long bid);

    //댓글 작성
    int commentInsert(Comment comment);
}
