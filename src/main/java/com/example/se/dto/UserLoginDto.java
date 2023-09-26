package com.example.se.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {
    private String userId;
    private String userPw;
}
