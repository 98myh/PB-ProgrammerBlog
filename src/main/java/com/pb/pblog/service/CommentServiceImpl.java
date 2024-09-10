package com.pb.pblog.service;

import com.pb.pblog.config.auth.CustomUserDetails;
import com.pb.pblog.dto.CommentDTO;
import com.pb.pblog.dto.CommentEditDTO;
import com.pb.pblog.dto.CommentSaveDTO;
import com.pb.pblog.entity.Board;
import com.pb.pblog.entity.Comment;
import com.pb.pblog.entity.User;
import com.pb.pblog.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentServiceImpl implements CommentService{
    private final CommentMapper commentMapper;

    //댓글 쓰기
    @Override
    public int commentInsert(CommentSaveDTO commentSaveDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Comment comment=Comment.builder()
                .board(Board.builder().bid(commentSaveDTO.getBid()).build())
                .user(User.builder().uid(userDetails.getUid()).build())
                .parent_cid(commentSaveDTO.getParent_cid())
                .comment(commentSaveDTO.getComment())
                .create_date(LocalDateTime.now())
                .update_date(LocalDateTime.now())
                .build();

        int result=commentMapper.commentInsert(comment);

        return result;
    }

    //댓글 삭제
    @Override
    public int commentDelete(Long cid) {
        //하위 댓글이 있을경우도 생각해야함

        int result=commentMapper.commentDelete(cid);
        return result;
    }

    //댓글 수정
    @Override
    public int commentEdit(CommentEditDTO commentEditDTO) {
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails=(CustomUserDetails) authentication.getPrincipal();

            Comment comment=Comment.builder()
                    .cid(commentEditDTO.getCid())
                    .comment(commentEditDTO.getComment())
                    .update_date(LocalDateTime.now())
                    .build();

            int result=commentMapper.commentEdit(comment);
            return result;
        }catch (Exception e){
            log.error(e);
            return 0;
        }
    }
}
