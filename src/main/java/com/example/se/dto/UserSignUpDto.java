package com.example.se.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data //Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor 모두 포함
//@Builder
@AllArgsConstructor
public class UserSignUpDto {
    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String userPw;

    @NotBlank(message = "이름을 입력해주세요")
    private String userName;

    @NotBlank(message = "학과를 입력해주세요")
    private String userMajor;

    @NotBlank(message = "상태(재학/휴학/졸업)를 입력해주세요")
    private String userState;

    @NotNull(message = "생년월일을 입력해주세요") //@NotBlank는 localdata 지원 안하는듯
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirth;

}
