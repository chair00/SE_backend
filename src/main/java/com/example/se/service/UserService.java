package com.example.se.service;

import com.example.se.dto.UserDto;
import com.example.se.dto.UserLoginDto;
import com.example.se.dto.UserSignUpDto;
import com.example.se.entity.Users;
import com.example.se.repository.UserRepository;
import com.example.se.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //롤백 기능 어노테이션. readonly쓰면 성능면에서 좋다네
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(UserSignUpDto userDto) throws Exception {
        if (userRepository.findByUserId(userDto.getUserId()).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        Users user = Users.builder()
                .userId(userDto.getUserId())
                .userPw(passwordEncoder.encode(userDto.getUserPw()))
                .userName(userDto.getUserName())
                .userMajor(userDto.getUserMajor())
                .userState(userDto.getUserState())
                .userBirth(userDto.getUserBirth())
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }

    public String login(@RequestBody UserLoginDto userLoginDto) {
        Users user = userRepository.findByUserId(userLoginDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원 입니다."));
        if (!passwordEncoder.matches(userLoginDto.getUserPw(), user.getUserPw())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUsername(), user.getRole());
    }

    public UserDto findUser(String userId) {
        Users user = userRepository.findByUserId(userId).orElseThrow(() -> new BadCredentialsException("회원 정보를 찾을 수 없습니다."));
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userMajor(user.getUserMajor())
                .userState(user.getUserState())
                .userBirth(user.getUserBirth())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
    }

    @Transactional
    public void updateMyInfo(Users user) {
        Users persistance = userRepository.findByUserId(user.getUserId()).orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        persistance.setUserPw(passwordEncoder.encode(user.getUserPw()));
        persistance.setUserName(user.getUserName());
        //더티체킹. 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌(db에)
    }
}
