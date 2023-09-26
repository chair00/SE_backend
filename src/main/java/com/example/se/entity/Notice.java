package com.example.se.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String noticeTitle;

    @Column
    private String noticeContent;

    public void update(String title, String content){ //더티체킹(dirty checking)
        noticeTitle = title;
        noticeContent = content;
    }
}
