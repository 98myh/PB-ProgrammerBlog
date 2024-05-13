package com.pb.pblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {
    private Long uid;
    private String title;
    private String category;
    private String content;
}
