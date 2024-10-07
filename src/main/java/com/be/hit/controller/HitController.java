package com.be.hit.controller;

import com.be.auth.JwtProvider;
import com.be.auth.JwtUtils;
import com.be.hit.dto.req.HitRequestDto;
import com.be.hit.service.AgeGroupProductHitsService;
import com.be.hit.service.PreferenceProductHitsService;
import com.be.hit.service.TopFinanceService;
import com.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/hit")
@RequiredArgsConstructor
@Slf4j
public class HitController {

    private final AgeGroupProductHitsService ageGroupProductHitsService;
    private final PreferenceProductHitsService preferenceProductHitsService;
    private final TopFinanceService topFinanceService;
    private final JwtUtils jwtUtils;

    // 프론트엔드에서 상품 ID를 받아 조회수를 증가시키는 API
    @PostMapping("ageGroup/increase")
    public ResponseEntity<String> increaseProductHit(HttpServletRequest request, @RequestBody HitRequestDto hitRequestDto) {

        // JWT에서 memberNum 추출
        long memberNum = jwtUtils.extractMemberNum(request);

        // 연령대 계산 (memberNum을 통해 생년월일 조회 후 연령대 계산)
        int ageGroup = ageGroupProductHitsService.calculateAgeGroup(memberNum);

        // 서비스 레이어에 요청하여 조회수 증가 처리
        ageGroupProductHitsService.increaseProductHit(ageGroup, hitRequestDto.getProductId());
        return ResponseEntity.ok("Hit count increased successfully.");
    }

    // 연령대별 상위 3개 상품을 조회하는 API
    @GetMapping("ageGroup/top-products")
    public ResponseEntity<List<Object>> getTopProductsByAgeGroup(HttpServletRequest request) {
        // jwtUtils에서 memberNum 추출
        long memberNum = jwtUtils.extractMemberNum(request);

        // memberNum으로 생년월일 조회 및 연령대 계산
        int ageGroup = ageGroupProductHitsService.calculateAgeGroup(memberNum);

        // 연령대별 상위 3개의 상품 ID를 조회
        List<Integer> topProductIds = ageGroupProductHitsService.findTop3ProductsByAgeGroup(ageGroup);

        // 각 상품 ID에 맞는 상세 정보를 조회
        List<Object> topProducts = topFinanceService.getTop3ProductsByAgeGroup(ageGroup, topProductIds);

        return ResponseEntity.ok(topProducts);
    }

    // 투자성향과 상품 ID를 받아 조회수를 증가시키는 API
    @PostMapping("/preference/increase")
    public ResponseEntity<String> increasePreferenceProductHit(HttpServletRequest request, @RequestBody HitRequestDto hitRequestDto) {

        // JWT에서 memberNum 추출
        long memberNum = jwtUtils.extractMemberNum(request);

        // memberNum으로 투자 성향 조회
        int preference = preferenceProductHitsService.getUserPreference(memberNum);

        // 조회수 증가 처리
        preferenceProductHitsService.increaseProductHit(preference,hitRequestDto.getProductId());

        return ResponseEntity.ok("Preference-based hit count increased successfully.");
    }

    // 투자성향별 상위 3개 상품을 조회하는 API
    @GetMapping("/preference/top-products")
    public ResponseEntity<List<Object>> getTopProductsByPreference(HttpServletRequest request) {
        // jwtUtils에서 memberNum 추출
        long memberNum = jwtUtils.extractMemberNum(request);

        // memberNum으로 투자성향 조회
        int preference = preferenceProductHitsService.getUserPreference(memberNum);

        // 투자성향별 상위 3개의 상품 ID 조회
        List<Integer> topProductIds = preferenceProductHitsService.findTop3ProductsByPreference(preference);

        // 각 상품 ID에 맞는 상세 정보를 조회
        List<Object> topProducts = topFinanceService.getTop3ProductsByPreference(preference, topProductIds);

        return ResponseEntity.ok(topProducts);
    }
}