package com.be.auth;

import com.be.member.domain.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
    private final JwtProvider jwtProvider;

    public JwtUtils(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    // 토큰에서 memberNum 가져오기
    public long extractMemberNum(HttpServletRequest request) {
        String token = request.getHeader("Refresh-Token") == null ?
                request.getHeader(HttpHeaders.AUTHORIZATION) :
                request.getHeader("Refresh-Token");

        Member member = jwtProvider.authenticateJwt(token);
        return member.getMemberNum(); // 사용자 번호 추출
    }

    // 토큰에서 preference 가져오기
    public int extractPreference(HttpServletRequest request) {
        String token = request.getHeader("Refresh-Token") == null ?
                request.getHeader(HttpHeaders.AUTHORIZATION) :
                request.getHeader("Refresh-Token");

        Member member = jwtProvider.authenticateJwt(token);
        return member.getPreference(); // 사용자 투자 성향 추출
    }
}
