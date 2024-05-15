package com.pb.pblog.service;

import com.pb.pblog.config.auth.CustomUserDetails;
import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.BoardResponseDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.dto.CommentAndUserDTO;
import com.pb.pblog.entity.Board;
import com.pb.pblog.entity.Comment;
import com.pb.pblog.entity.User;
import com.pb.pblog.repository.BoardMapper;
import com.pb.pblog.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    //게시판 mapper
    private final BoardMapper boardMapper;

    //댓글 mapper
    private final CommentMapper commentMapper;

    //이미지 저장 경로
    private static final String BASE_UPLOAD_FOLDER = "D:/image/";

    //이미지 저장
    @Override
    public String imageSave(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "No file uploaded.";
            }

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

            String dateFolder = now.format(dateFormatter);
            String timestamp = now.format(timeFormatter);

            Path datePath = Paths.get(BASE_UPLOAD_FOLDER + dateFolder);
            if (!Files.exists(datePath)) {
                Files.createDirectories(datePath);  // 날짜 폴더 생성
            }

            String newFileName = timestamp + "_" + file.getOriginalFilename();  // 파일 이름 설정
            Path filePath = datePath.resolve(newFileName);
            byte[] bytes = file.getBytes();
            Files.write(filePath, bytes);  // 파일 저장


            String fileDownloadUri = "/images/" + dateFolder + "/" + newFileName;  // 웹 접근 경로 반환

            return fileDownloadUri;
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    //글 저장
    @Override
    public int boardSave(BoardSaveDTO boardSaveDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            Board board=Board.builder()
                    .user(User.builder()
                            .uid(userDetails.getUid())
                            .build())
                    .title(boardSaveDTO.getTitle())
                    .category(boardSaveDTO.getCategory())
                    .content(boardSaveDTO.getContent())
                    .build();

            boardMapper.boardSave(board);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    //게시글 조회
    @Override
    public List<BoardAndUserDTO> boardSearch(String category) {
        List<Board>boards=boardMapper.boardSearch(category,null);

        //DTO로 형변환
        List<BoardAndUserDTO> boardAndUserDTOS = boards.stream()
                .map(board -> boardAndUserEntityToDTO(board))
                .collect(Collectors.toList());

        return boardAndUserDTOS;
    }

    //게시글 상세 조회
    @Override
    public BoardResponseDTO boardDetails(Long bid) {
        //게시글 정보
        Board board=boardMapper.boardDetail(bid);
        BoardAndUserDTO boardAndUserDTO=boardAndUserEntityToDTO(board);


        //댓글 정보
        List<Comment> comments=commentMapper.boardComment(bid);

        //DTO로 형변환
        List<CommentAndUserDTO> commentAndUserDTOS = comments.stream()
                .map(comment -> CommentAndUserDTO.builder()
                        .bid(comment.getBid())
                        .cid(comment.getCid())
                        .parent_cid(comment.getParent_cid())
                        .uid(comment.getUser().getUid())
                        .nickname(comment.getUser().getNickname())
                        .comment(comment.getComment())
                        .create_date(comment.getCreate_date())
                        .update_date(comment.getUpdate_date())
                        .build())
                .collect(Collectors.toList());
        BoardResponseDTO boardResponseDTO=BoardResponseDTO.builder()
                .boardAndUserDTO(boardAndUserDTO)
                .commentAndUserDTOS(commentAndUserDTOS)
                .build();

        return boardResponseDTO;
    }


}
