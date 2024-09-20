package com.be.user.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterReqDto {

    private String userID;
    private String userEmail;
    private String userName;
    private String userPw;
    private String userBirth;
    private char userGender;

}
