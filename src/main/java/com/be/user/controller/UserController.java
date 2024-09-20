package com.be.user.controller;


import com.be.user.dto.req.UserRegisterReqDto;
import com.be.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public String register(@RequestBody UserRegisterReqDto reqDto) {
        int state = userService.registerUser(reqDto);

        if (state == 1) {
            return "success";
        }
        return "fail";
    }
}
