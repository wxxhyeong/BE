package com.be.member.controller;


import com.be.auth.JwtProvider;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import com.be.common.dto.DefaultResDto;
import com.be.member.domain.Member;
import com.be.member.dto.req.InvestPreferenceReqDto;
import com.be.member.dto.req.MemberLoginReqDto;
import com.be.member.dto.req.MemberRegisterReqDto;
import com.be.member.dto.req.MemberResponseReqDto;
import com.be.member.dto.res.MemberDefaultResDto;
import com.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static com.be.common.code.SuccessCode.MEMBER_LOGIN;
import static com.be.common.code.SuccessCode.USER_REGISTERED;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final CartService cartService;

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

        HttpHeaders headers = jwtProvider.generateUserJwt(member.getMemberNum(), member.getRoles());
        MemberDefaultResDto response = new MemberDefaultResDto(member);

        // 로그인 시 장바구니 DB 조회 및 세션에 저장
        HttpSession session = request.getSession();
        List<CartItemResDto> cartList = cartService.getCartList(member.getMemberNum());
        session.setAttribute("cartList", cartList);

        log.info(session.getAttribute("cartList").toString());
        //

        log.info(response.toString());
        return ResponseEntity.status(MEMBER_LOGIN.getHttpStatus())
                .headers(headers)
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(MEMBER_LOGIN.name())
                        .responseMessage(MEMBER_LOGIN.getMessage())
                        .data(response)
                        .build());
    }


    // 투자 성향 점수 업데이트 요청 처리
    @PostMapping("/investPreference")
    public ResponseEntity<String> updateInvestPreference(@RequestBody InvestPreferenceReqDto request) {
        if (request.getMemberNum() == null || request.getInvestScore() == null) {
            return ResponseEntity.badRequest().body("memberNum 또는 investScore가 유효하지 않습니다.");
        }

        int updatedRows = memberService.updateMemberPreference(request.getMemberNum(), request.getInvestScore());
        if (updatedRows > 0) {
            return ResponseEntity.ok("Update success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No update item");
        }
    }


    // 사용자의 투자 성향 점수와 성향을 조회하는 메서드 (GET)
    @GetMapping("/{memberNum}/investPreference")
    public ResponseEntity<MemberResponseReqDto> getInvestPreference(@PathVariable("memberNum") Long memberNum) {
        MemberResponseReqDto memberResponse = memberService.getMemberPreference(memberNum);

        if (memberResponse != null) {
            return ResponseEntity.ok(memberResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 해당 사용자가 없을 경우
        }
    }
}
