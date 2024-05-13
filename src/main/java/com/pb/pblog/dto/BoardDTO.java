package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long uid;
    private Long bid;
    private String title;
    private String category;
    private String content;
    private LocalDateTime carete_date;
    private LocalDateTime update_date;

}
