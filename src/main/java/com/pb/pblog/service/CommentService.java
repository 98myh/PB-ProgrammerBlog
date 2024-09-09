package com.pb.pblog.service;

import com.pb.pblog.dto.CommentDTO;
import com.pb.pblog.dto.CommentEditDTO;
import com.pb.pblog.dto.CommentSaveDTO;

public interface CommentService {

    //댓글 작성하기
    int commentInsert(CommentSaveDTO commentSaveDTO);

    //댓글 삭제
    int commentDelete(Long cid);

    //댓글 수정
    int commentEdit(CommentEditDTO commentEditDTO);

}
