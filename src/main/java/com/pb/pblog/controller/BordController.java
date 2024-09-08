package com.pb.pblog.controller;

import com.pb.pblog.dto.*;
import com.pb.pblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BordController {

    private final BoardService boardService;

    //model 넣어서 보내서 거기서 model에 따라 쿼리문 다르게 사용하여 불러와서 조회
    //게시글 페이지
    @GetMapping("/{category}")
    public String boardList(@PathVariable String category, @RequestParam(required = false) String title, Model model){
        List<BoardAndUserDTO> boardList=boardService.boardSearch(category,title);
        for (BoardAndUserDTO board : boardList) {
            String content = board.getContent();
            Document doc = Jsoup.parse(content);
            Elements images = doc.select("img");
            String firstImgSrc = images.size() > 0 ? images.get(0).attr("src") : "/resources/images/pblogo.png";
            board.setContent(firstImgSrc);
        }
        String midTitle="";
        if(category.equals("all")){
            midTitle="모두";
        }
        else if(category.equals("recently")){
            midTitle="최근 게시글";
        }else if(category.equals("trend")){
            midTitle="개발동향";
        }else if(category.equals("skill")){
            midTitle="개발스킬";
        } else if (category.equals("algorithm")) {
            midTitle="알고리즘";
        }else{
            midTitle="기타";
        }


        model.addAttribute("board",boardList);
        model.addAttribute("category",midTitle);


        return "redirect:/board/board";
    }

    //게시글 쓰기페이지로 이동
    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    //이미지 저장
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
//    @PostMapping("/save")
//    public String boardSave(@ModelAttribute BoardSaveDTO boardSaveDTO){
//        int bid=boardService.boardSave(boardSaveDTO);
//        return "/board/recently";
//    }

    @PostMapping("/save")
//    @ResponseBody  // JSON 데이터를 반환하기 위해 사용
    public ResponseEntity<Map<String, Object>> boardSave(@ModelAttribute BoardSaveDTO boardSaveDTO){
        int bid = boardService.boardSave(boardSaveDTO);
        Map<String, Object> response = new HashMap<>();

        if (bid > 0) {
            response.put("result", "success");
            response.put("redirectUrl", "/board/recently");
        } else {
            response.put("result", "fail");
        }

        return ResponseEntity.ok(response);  // JSON 형태로 응답
    }

    //게시글 상세보기
    @GetMapping("/detail/{bid}")
    public String boardDetail(@PathVariable Long bid,Model model){
        BoardResponseDTO boardResponseDTO= boardService.boardDetails(bid);
        model.addAttribute("board_detail",boardResponseDTO);
        return "board/boardDetail";
    }

    //게시글 수정 페이지로 이동
    @GetMapping("/edit/{bid}")
    public String boardEdit(@PathVariable Long bid,Model model){
        BoardResponseDTO boardResponseDTO= boardService.boardDetails(bid);
        model.addAttribute("board",boardResponseDTO);
        return "board/write";
    }

    //게시글 수정
    @ResponseBody
    @PutMapping("/edit")
    public ResponseEntity<?> boardEditSave(@RequestBody BoardEditDTO boardEditDTO){

        int result=boardService.boardEdit(boardEditDTO);
        //수정 성공
        if (result==1){
            return new ResponseEntity<>(boardEditDTO.getBid(),HttpStatus.OK) ;
        }
        //수정 실패
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //게시글 삭제
    @ResponseBody
    @DeleteMapping("/delete")
    public ResponseEntity<?> boardDelete(@RequestBody BidDTO bidDTO){

        int result=boardService.boardDelete(bidDTO.getBid());
        if (result==1){ //1일경우 => 삭제된 데이터가 하나이다. 즉, 삭제가 완료되었다.
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

}
