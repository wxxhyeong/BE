package com.be.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class User {

    private int userNum;
    private String userID;
    private String userName;
    private String userEmail;
    private String userPw;
    private String userBirth;
    private char userGender;
    private int userPreference;
    private int userInvestScore;
    private String userRegDate;
}
