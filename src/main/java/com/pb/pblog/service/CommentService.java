package com.pb.pblog.service;

import com.pb.pblog.dto.CommentSaveDTO;

public interface CommentService {

    //댓글 작성하기
    int commentInsert(CommentSaveDTO commentSaveDTO);

}
