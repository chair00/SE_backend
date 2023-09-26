package com.example.se.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeDto {
    private Long id;

    private String noticeTitle;

    private String noticeContent;
}
