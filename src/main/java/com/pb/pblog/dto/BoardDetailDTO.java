package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {
    private Long bid;
    private String title;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private String category;

    private Long uid;
    private String nickname;
}
