package com.be.user.controller;


import com.be.user.dto.req.UserRegisterReqDto;
import com.be.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String tesss() {
        log.info("aget");
        return "tess";
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRegisterReqDto reqDto) {

        log.info("register = " + reqDto.toString());
        int state = userService.registerUser(reqDto);

        if (state == 1) {
            return "success";
        }
        return "fail";
    }
}
