package com.pb.pblog.service;


import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.entity.Board;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    //이미지 저장
    String imageSave(MultipartFile file);

    //글 저장
    int boardSave(BoardSaveDTO boardSaveDTO);

    //글 조회
    List<BoardAndUserDTO> boardSearch(String category);
}
