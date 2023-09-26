package com.example.se.controller;

import com.example.se.dto.NoticeDto;
import com.example.se.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/notice")
@RestController
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;

    //공지사항 생성
    @PostMapping("")
    public ResponseEntity<String> createNotice(@RequestBody NoticeDto noticeDto){
        noticeService.createNotice(noticeDto);
        return new ResponseEntity<>("create", HttpStatus.OK);
    }

    //공지사항 조회
    @GetMapping("/{id}")
    public NoticeDto readNotice(@PathVariable Long id){
        return noticeService.findNotice(id);
    }

    //공지사항 수정
    //생성시간, 수정시간도 뜨게 dto 변수 추가 후 수정하기
    @PatchMapping("/{id}")
    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeDto noticeDto){
        return noticeService.updateNotice(id, noticeDto);
    }

    //공지사항 삭제
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long id){
        noticeService.deleteNotice(id);
        return new ResponseEntity<>("delete", HttpStatus.OK);
    }

}
