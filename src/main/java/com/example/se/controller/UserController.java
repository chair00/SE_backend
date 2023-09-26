package com.example.se.controller;

import com.example.se.dto.NoticeDto;
import com.example.se.dto.UserDto;
import com.example.se.dto.UserLoginDto;
import com.example.se.dto.UserSignUpDto;
import com.example.se.entity.Users;
import com.example.se.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp( //Exception 안넣어주니까 오류 나던데 왜지? @NotNull과 관련?
            @Valid @RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return new ResponseEntity<>("회원가입에 성공했습니다.", HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login( //얘는 Exception 없어도 오류 안남? String으로 왜 작동함? service가 리턴하는게 string이라서?
            @Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

    @GetMapping("/mypage")
    @ResponseBody
    public UserDto getMyInfo(@AuthenticationPrincipal Users user) { //authentication이 뭐임? 이것만 인자로 넘겨줘도 알아서 찾을 수 있는거임?
        if(user == null) {
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.");
        }
        log.info("일단 controller는 옴");
        return userService.findUser(user.getUserId());
    }

    @PatchMapping("/mypage/edit")
    public ResponseEntity<String> updateMyInfo(@RequestBody Users user){ //얘도 AuthenticationPrincipal 어노테이션 쓰면 되는걸까? 일단 entity를 매개변수로 넘겨주긴 해야함
        userService.updateMyInfo(user);
        //여기서 트랜잭션이 종료돼서 db값은 변경이됨.
        //하지만 세션값은 변경되지 않은 상태이기 때문에 세션값 갱신이 필요함

        //세션 등록

        return new ResponseEntity<>("회원 정보 수정 완", HttpStatus.OK);
    }
}
