package com.pb.pblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BordController {

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
}
