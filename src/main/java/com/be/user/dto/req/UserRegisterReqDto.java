package com.be.user.dto.req;

import com.be.user.dto.res.UserRegisterResDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class UserRegisterReqDto {
    {
        log.info("UserRegisterReqDto constructor");
    }


    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Size(min = 5, max = 15, message = "아이디는 5~15자만 가능합니다.")
    @Pattern(regexp = "^(?=.*[a-z0-9])[a-z0-9]+$", message = "아이디는 소문자 영어와 숫자의 조합으로 입력해 주세요.")
    private String userID;

    private String userEmail;

    private String userName;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 8~30자만 가능합니다.")
    @Pattern(regexp = "^(?=.*[A-z])(?=.*\\d)(?=.*[#$@!%&*?])[A-z\\d#$@!%&*?]+$",
            message = "비밀번호는 영어, 숫자, 특수문자(#$@!%&*?)의 조합으로 입력해 주세요.")
    private String userPassword;

    @NotBlank(message = "비밀번호 재입력은 필수 입력입니다.")
    private String reEnteredPassword;

    private String userBirth;

    @Pattern(regexp = "^(남|여)", message = "성별을 선택해주세요.")
    private String userGender;

    public UserRegisterResDto toUserResDto(String encodedPassword) {

        return UserRegisterResDto.builder()
                .userID(this.userID)
                .userEmail(this.getUserEmail())
                .userName(this.getUserName())
                .userPassword(encodedPassword)
                .userBirth(this.getUserBirth())
                .userGender(this.getUserGender())
                .userRegDate(LocalDate.now().toString()).build();

    }
}
