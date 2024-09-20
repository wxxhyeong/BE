package com.be.user.service;

import com.be.user.dto.req.UserRegisterReqDto;
import com.be.user.dto.res.UserRegisterResDto;
import com.be.user.mapper.UserMapper;
import com.be.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public int registerUser(UserRegisterReqDto reqDto) {
        UserRegisterResDto resDto = UserRegisterResDto.builder()
                .userID(reqDto.getUserID())
                .userEmail(reqDto.getUserEmail())
                .userName(reqDto.getUserName())
                .userPw(reqDto.getUserPw())
                .userBirth(reqDto.getUserBirth())
                .userGender(reqDto.getUserGender())
                .userRegDate(LocalDate.now().toString()).build();

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


}
