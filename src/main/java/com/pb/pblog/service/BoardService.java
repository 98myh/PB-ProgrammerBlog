package com.pb.pblog.service;


import com.pb.pblog.dto.BoardSaveDTO;

public interface BoardService {
    //글 저장
    int boardSave(BoardSaveDTO boardSaveDTO);
}
