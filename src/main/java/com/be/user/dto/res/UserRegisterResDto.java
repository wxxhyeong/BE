package com.be.user.dto.res;


import lombok.*;

@Getter
@Builder
public class UserRegisterResDto {

    private String userID;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userBirth;
    private String userGender;
    private String userRegDate;
}
