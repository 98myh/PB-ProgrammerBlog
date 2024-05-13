package com.pb.pblog.controller;

import com.pb.pblog.config.auth.CustomUserDetails;
import com.pb.pblog.dto.BoardDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BordController {

    private final BoardService boardService;

    private static final String BASE_UPLOAD_FOLDER = "D:/image/";

    //model 넣어서 보내서 거기서 model에 따라 쿼리문 다르게 사용하여 불러와서 조회
    //인기 게시글 페이지
    @GetMapping("/popular")
    public String popular(){
        return "board/board";
    }

    //최근 게시글 페이지
    @GetMapping("/recently")
    public String recently(){
        return "board/board";
    }

    //게시글 쓰기
    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    //이미지 저장 - 추후 로직 분리해야함
    @PostMapping("/img-upload")
    public ResponseEntity<?> imgUpload(@RequestParam("image") MultipartFile file){
        System.out.println("파일"+file);
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file uploaded.");
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

            String fileDownloadUri = filePath.toString();

            return ResponseEntity.ok(fileDownloadUri);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file: " + ex.getMessage());
        }
    }

    //글 저장
    @PostMapping("/save")
    public String boardSave(@ModelAttribute BoardSaveDTO boardSaveDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        boardSaveDTO.setUid(userDetails.getUid());
        boardService.boardSave(boardSaveDTO);
        return "redirect:/board/recently";
    }
}
