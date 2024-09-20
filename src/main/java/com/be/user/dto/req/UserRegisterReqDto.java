package com.be.user.dto.req;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

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

    private String userID;
    private String userEmail;
    private String userName;
    private String userPw;
    private String userBirth;
    private char userGender;

}
