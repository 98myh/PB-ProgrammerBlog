package com.pb.pblog.controller;

import com.pb.pblog.dto.BoardAndUserDTO;
import com.pb.pblog.dto.BoardResponseDTO;
import com.pb.pblog.dto.BoardSaveDTO;
import com.pb.pblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BordController {

    private final BoardService boardService;


    //model 넣어서 보내서 거기서 model에 따라 쿼리문 다르게 사용하여 불러와서 조회
    //게시글 페이지
    @GetMapping("/{category}")
    public String recently(@PathVariable String category,Model model){
        List<BoardAndUserDTO> boardList=boardService.boardSearch(category);
        for (BoardAndUserDTO board : boardList) {
            String content = board.getContent();
            Document doc = Jsoup.parse(content);
            Elements images = doc.select("img");
            String firstImgSrc = images.size() > 0 ? images.get(0).attr("src") : "default.jpg";
            board.setContent(firstImgSrc);
        }
        model.addAttribute("board",boardList);


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
        try {
            String url=boardService.imageSave(file);
            return ResponseEntity.ok(url);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file: " + exception.getMessage());
        }
    }

    //글 저장
    @PostMapping("/save")
    public String boardSave(@ModelAttribute BoardSaveDTO boardSaveDTO){
        boardService.boardSave(boardSaveDTO);
        return "redirect:/board/recently";
    }

    //게시글 상세보기
    @RequestMapping("/detail/{bid}")
    public String boardDetail(@PathVariable Long bid,Model model){
        BoardResponseDTO boardResponseDTO= boardService.boardDetails(bid);
        model.addAttribute("board_detail",boardResponseDTO);
        return "board/boardDetail";
    }

}
