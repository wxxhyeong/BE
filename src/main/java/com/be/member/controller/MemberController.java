package com.be.member.controller;


import com.be.auth.JwtProvider;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import com.be.common.dto.DefaultResDto;
import com.be.member.domain.Member;
import com.be.member.dto.req.MemberLoginReqDto;
import com.be.member.dto.req.MemberRegisterReqDto;
import com.be.member.dto.res.MemberDefaultResDto;
import com.be.member.service.MemberService;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.List;

import static com.be.common.code.SuccessCode.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final CartService cartService;
    private final PortfolioService portfolioService;

    @GetMapping("")
    public String tesss() {
        log.info("aget");
        return "tess";
    }


    @PostMapping("/register")
    public ResponseEntity<DefaultResDto<Object>> register(HttpServletRequest servletRequest, @RequestBody @Valid MemberRegisterReqDto reqDto) {
        //jwtProvider.authorizeGuestAccessJwt(servletRequest.getHeader(AUTHORIZATION));

        log.info("register = " + reqDto.toString());
        Member member = memberService.registerMember(reqDto);

        HttpHeaders headers = jwtProvider.generateUserJwt(member.getMemberNum(), member.getRoles());
        MemberDefaultResDto response = new MemberDefaultResDto(member);

        return ResponseEntity.status(USER_REGISTERED.getHttpStatus())
                .headers(headers)
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(USER_REGISTERED.name())
                        .responseMessage(USER_REGISTERED.getMessage())
                        .data(response)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<DefaultResDto<Object>> login(@RequestBody @Valid MemberLoginReqDto memberLoginReqDto, HttpServletRequest request) {
        Member member = memberService.login(memberLoginReqDto);
        log.info("member Roles = " + member.getRoles().toString());

        HttpHeaders headers = jwtProvider.generateUserJwt(member.getMemberNum(), member.getRoles());
        MemberDefaultResDto response = new MemberDefaultResDto(member);

        return ResponseEntity.status(USER_LOGIN.getHttpStatus())
                .headers(headers)
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(USER_LOGIN.name())
                        .responseMessage(USER_LOGIN.getMessage())
                        .data(response)
                        .build());
    }

    @GetMapping()
    public ResponseEntity<DefaultResDto<Object>> findMyInfo(HttpServletRequest servletRequest) {
        Member member = jwtProvider.authorizeUserAccessJwt(servletRequest.getHeader(AUTHORIZATION));

        MemberDefaultResDto response = new MemberDefaultResDto(member);

        return ResponseEntity.status(SELF_USER_FOUND.getHttpStatus())
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(SELF_USER_FOUND.name())
                        .responseMessage(SELF_USER_FOUND.getMessage())
                        .data(response)
                        .build());
    }

    @GetMapping("/logout")
    public ResponseEntity<DefaultResDto<Object>> logout(HttpServletRequest request) {
        return ResponseEntity.status(USER_LOGOUT.getHttpStatus())
                .body(DefaultResDto.noDataBuilder()
                        .responseCode(USER_LOGOUT.name())
                        .responseMessage(USER_LOGOUT.getMessage())
                        .build());
    }





}
