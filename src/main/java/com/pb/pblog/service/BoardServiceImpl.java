package com.pb.pblog.service;

import com.pb.pblog.config.auth.CustomUserDetails;
import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.entity.Board;
import com.pb.pblog.repository.BoardMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    //게시판 mapper
    private final BoardMapper boardMapper;

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

//            String fileDownloadUri = filePath.toString();

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
            boardSaveDTO.setUid(userDetails.getUid());
            boardMapper.boardSave(boardSaveDTO);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    //게시글 조회
    @Override
    public List<BoardAndUserDTO> boardSearch(String category) {
        List<BoardAndUserDTO>boards=boardMapper.boardSearch(category,null);
        return boards;
    }


}
