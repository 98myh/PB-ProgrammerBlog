package com.pb.pblog.service;

public interface CommentService {

    //댓글 작성하기


    //DTO -> Entity
//    default CommentAndUser commentAndUserDtoToEntity(CommentAndUserDTO commentAndUserDTO){
//        return CommentAndUser.builder()
//                .bid(commentAndUserDTO.getBid())
//                .cid(commentAndUserDTO.getCid())
//                .parent_cid(commentAndUserDTO.getParent_cid())
//                .uid(commentAndUserDTO.getUid())
//                .nickname(commentAndUserDTO.getNickname())
//                .comment(commentAndUserDTO.getComment())
//                .create_date(commentAndUserDTO.getCreate_date())
//                .update_date(commentAndUserDTO.getUpdate_date())
//                .build();
//    }
//
//    //Entity -> DTO
//    default CommentAndUserDTO commentAndUserEntityToDTO(CommentAndUser commentAndUser){
//        return CommentAndUserDTO.builder()
//                .bid(commentAndUser.getBid())
//                .cid(commentAndUser.getCid())
//                .parent_cid(commentAndUser.getParent_cid())
//                .uid(commentAndUser.getUid())
//                .nickname(commentAndUser.getNickname())
//                .comment(commentAndUser.getComment())
//                .create_date(commentAndUser.getCreate_date())
//                .update_date(commentAndUser.getUpdate_date())
//                .build();
//    }
}
