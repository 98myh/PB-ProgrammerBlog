package com.pb.pblog.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDTO {
    private Long uid;
    private String oldPassword;
    private String newPassword;
    private String nickname;
}
