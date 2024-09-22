package com.be.user.service;

import com.be.common.code.ErrorCode;
import com.be.exception.CustomException;
import com.be.user.domain.User;
import com.be.user.dto.req.UserRegisterReqDto;
import com.be.user.dto.res.UserRegisterResDto;
import com.be.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

import static com.be.common.code.ErrorCode.*;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Bean
    private PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}

    public int registerUser(@RequestBody @Valid UserRegisterReqDto reqDto) {

        validateUserID(reqDto.getUserID());
        validateUserEmail(reqDto.getUserEmail());
        checkPasswordMatching(reqDto.getUserPassword(), reqDto.getReEnteredPassword());
        String encodedPassword = encodePassword(reqDto.getUserPassword());
        UserRegisterResDto resDto = reqDto.toUserResDto(encodedPassword);

        try {
            userMapper.insert(resDto);
            log.info("User registration successful");

            return 1;
        } catch (Exception e) {
            e.getStackTrace();
            log.error("Error registering user: {}", e.getMessage());

            return 0;
        }
    }

    public void validateUserID(String userID) {

        isExistID(userID);
    }

    public void validateUserEmail(String userEmail) {

        isExistUserEmail(userEmail);
    }

    public void checkPasswordMatching(String userPassword, String reEnteredPassword) {
        if (!userPassword.equals(reEnteredPassword))
            throw new CustomException(PASSWORD_MATCH_INVALID);
    }


    public String encodePassword(String userPw) {
        return passwordEncoder().encode(userPw);
    }

    public void isExistID(String userID) {
        Optional<User> user = Optional.ofNullable(userMapper.selectOneByUserID(userID));

        if (user.isPresent()) {
            throw new CustomException(EXISTING_USER_ID);
        }
    }

    public void isExistUserEmail(String userEmail) {
        Optional<User> user = Optional.ofNullable(userMapper.selectOneByUserEmail(userEmail));

        if (user.isPresent()) {
            throw new CustomException(EXISTING_EMAIL);
        }
    }




}
