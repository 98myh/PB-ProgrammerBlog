package com.pb.pblog.service;

import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    //게시판 mapper
    private final BoardMapper boardMapper;

    //글 저장
    @Override
    public int boardSave(BoardSaveDTO boardSaveDTO) {
        try {
            boardMapper.boardSave(boardSaveDTO);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
}
