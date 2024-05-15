package com.pb.pblog.service;


import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.BoardResponseDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.entity.Board;
import com.pb.pblog.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    //이미지 저장
    String imageSave(MultipartFile file);

    //글 저장
    int boardSave(BoardSaveDTO boardSaveDTO);

    //글 조회
    List<BoardAndUserDTO> boardSearch(String category);

    //게시글 상세 조회
    BoardResponseDTO boardDetails(Long bid);

    //DTO -> Entity
    default Board boardAndUserDtoToEntity(BoardAndUserDTO boardAndUserDTO){
        return Board.builder()
                .bid(boardAndUserDTO.getBid())
                .title(boardAndUserDTO.getTitle())
                .create_date(boardAndUserDTO.getCreate_date())
                .content(boardAndUserDTO.getContent())
                .update_date(boardAndUserDTO.getUpdate_date())
                .category(boardAndUserDTO.getCategory())
                .user(User.builder()
                        .uid(boardAndUserDTO.getUid())
                        .nickname(boardAndUserDTO.getNickname())
                        .build())
                .build();
    }

    //Entity -> DTO
    default BoardAndUserDTO boardAndUserEntityToDTO(Board board){
        return BoardAndUserDTO.builder()
                .bid(board.getBid())
                .uid(board.getUser().getUid())
                .title(board.getTitle())
                .create_date(board.getCreate_date())
                .content(board.getContent())
                .update_date(board.getUpdate_date())
                .category(board.getCategory())
                .nickname(board.getUser().getNickname())
                .build();
    }

}
