package com.pb.pblog.controller;

import com.pb.pblog.dto.CommentSaveDTO;
import com.pb.pblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    //댓글 쓰기
    @PostMapping("/save/{bid}/{cid}")
    public String commentSave(@PathVariable Long bid, @PathVariable(required = false) Long cid, @ModelAttribute String comment ){
        int result=commentService.commentInsert(CommentSaveDTO.builder()
                        .bid(bid)
                        .parent_cid(cid)
                        .comment(comment)
                .build());
        if(result!=1){
            return "Error";
        }
        return "/";
    }
}
