package com.example.se.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Record extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column //외래키로 받아야하나?
    private String userId;

    @Column
    private Long recordTime;

    @Column
    private LocalDate recordDate;
}
