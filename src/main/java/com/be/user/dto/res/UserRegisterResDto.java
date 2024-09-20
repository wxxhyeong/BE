package com.be.user.dto.res;


import lombok.*;

@Getter
@Builder
public class UserRegisterResDto {

    private String userID;
    private String userEmail;
    private String userName;
    private String userPw;
    private String userBirth;
    private char userGender;
    private String userRegDate;
}
