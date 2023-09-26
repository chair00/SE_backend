package com.example.se.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //BaseEntity를 상속한 Entity들은 아래 필드들을 칼럼으로 인식한다.
@EntityListeners(AuditingEntityListener.class) //Auditing(자동으로 값 매핑) 기능 추가
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
