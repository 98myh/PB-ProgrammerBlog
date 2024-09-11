package com.pb.pblog.controller;

import com.pb.pblog.dto.CidDTO;
import com.pb.pblog.dto.CommentEditDTO;
import com.pb.pblog.dto.CommentRequestDTO;
import com.pb.pblog.dto.CommentSaveDTO;
import com.pb.pblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    //댓글 쓰기
    @PostMapping("/save")
    public String commentSave(@ModelAttribute CommentRequestDTO commentRequestDTO, RedirectAttributes redirectAttributes){
        System.out.println(commentRequestDTO);
        Long bid=commentRequestDTO.getBid();
        int result=commentService.commentInsert(CommentSaveDTO.builder()
                        .bid(bid)
                        .parent_cid(commentRequestDTO.getParent_cid()!=null?commentRequestDTO.getParent_cid():null)
                        .comment(commentRequestDTO.getComment())
                .build());
        if(result!=1){
            return "Error";
        }

        //리다이렉트로 변수를 전달하기 위해 사용
        redirectAttributes.addAttribute("bid", bid);

        return "redirect:/board/detail/{bid}";
    }

    //댓글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> commentDelete(@RequestBody CidDTO cidDTO) {

        int result = commentService.commentDelete(cidDTO.getCid());

        if (result > 0) {
            return new ResponseEntity<>(true,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    //댓글 수정
    @PutMapping("/edit")
    public ResponseEntity<?> commentEdit(@RequestBody CommentEditDTO commentEditDTO){
        int result = commentService.commentEdit(commentEditDTO);
        if (result > 0) {
            return new ResponseEntity<>(true,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
