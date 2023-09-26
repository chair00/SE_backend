package com.example.se.service;

import com.example.se.dto.NoticeDto;
import com.example.se.entity.Notice;
import com.example.se.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public void createNotice(NoticeDto noticeDto) {
        Notice notice = new Notice(noticeDto.getId(), noticeDto.getNoticeTitle(), noticeDto.getNoticeContent());
        //dto에서는 id 생성 안했는데 이 생성자가 제대로 작동할까?
        log.info(notice.getId() + notice.getNoticeTitle() + notice.getNoticeContent());
        noticeRepository.save(notice);
    }

    public NoticeDto findNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));
        return NoticeDto.builder()
                .id(notice.getId())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .build();
    }

    @Transactional
    public Long updateNotice(Long id, NoticeDto noticeDto) { //Long으로 리턴해서 바뀐 게시글 id값을 받아올까? 고민
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));
        //db에 안날려도 알아서 업데이트 해준대.. 신기(더티체킹)
        notice.update(noticeDto.getNoticeTitle(), noticeDto.getNoticeContent());

        return id;
    }

    public void deleteNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));
        noticeRepository.delete(notice);
    }
}
