package com.example.se.repository;

import com.example.se.entity.Notice;
import com.example.se.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findById(Long id);
}
