package com.example.se.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter //없으면 회원 정보 조회(mypage)할 때 406 오류남
public class UserDto {
    private String userId;

    private String userName;

    private String userMajor;

    private String userState;

    private LocalDate userBirth;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
