package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportUserDTO {
    private long reporter_uid;  //원고
    private long defendant_uid; //피고
    private long bid;           //게시글
    private long cid;           //댓글
    private String reason;      //이유
}
