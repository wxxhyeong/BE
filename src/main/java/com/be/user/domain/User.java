package com.be.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class User {

    private int userNum;
    private String userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userBirth;
    private String userGender;
    private int userPreference;
    private int userInvestScore;
    private String userRegDate;
}
