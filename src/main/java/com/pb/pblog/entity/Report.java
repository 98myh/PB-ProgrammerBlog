package com.pb.pblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private String reason;              //이유
    private Boolean confirm;            //확인 여부
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    private User reporter;               //원고
    private User defendant;              //피고
    private Board board;
    private Comment comment;
}

