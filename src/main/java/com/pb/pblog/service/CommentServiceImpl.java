package com.pb.pblog.service;

import com.pb.pblog.config.auth.CustomUserDetails;
import com.pb.pblog.dto.CommentSaveDTO;
import com.pb.pblog.entity.Comment;
import com.pb.pblog.entity.User;
import com.pb.pblog.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private CommentMapper commentMapper;

    //댓글 쓰기
    @Override
    public int commentInsert(CommentSaveDTO commentSaveDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Comment comment=Comment.builder()
                .bid(commentSaveDTO.getBid())
                .user(User.builder().uid(userDetails.getUid()).build())
                .parent_cid(commentSaveDTO.getParent_cid())
                .comment(commentSaveDTO.getComment())
                .create_date(LocalDateTime.now())
                .update_date(LocalDateTime.now())
                .build();

        int result=commentMapper.commentInsert(comment);

        return result;
    }
}
