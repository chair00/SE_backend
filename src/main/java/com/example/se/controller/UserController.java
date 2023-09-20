package com.example.se.controller;

import com.example.se.entity.Users;
import com.example.se.repository.UserRepository;
import com.example.se.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/signup")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(Users.builder()
                .userId(user.get("userId"))
                .userPw(passwordEncoder.encode(user.get("userPw")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        Users member = userRepository.findByUserId(user.get("userId"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원 입니다."));
        if (!passwordEncoder.matches(user.get("userPw"), member.getUserPw())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
