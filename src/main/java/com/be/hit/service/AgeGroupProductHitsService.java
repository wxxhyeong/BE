package com.be.hit.service;

import com.be.hit.domain.AgeGroupProductHits;
import com.be.hit.mapper.AgeGroupProductHitsMapper;
import com.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgeGroupProductHitsService {
    private final AgeGroupProductHitsMapper ageGroupProductHitsMapper;
    private final MemberService memberService;

    // 상품 조회수를 증가시키는 메소드
    public void increaseProductHit(int ageGroup, int productId) {
        // 해당 상품과 연령대에 대한 조회수를 가져옴
        AgeGroupProductHits hits = ageGroupProductHitsMapper.findByAgeGroupAndProductId(ageGroup, productId);

        if (hits == null) {
            // 해당 데이터가 없으면 새로운 조회수 레코드를 추가
            hits = new AgeGroupProductHits();
            hits.setAgeGroup(ageGroup);
            hits.setProductId(productId);
            hits.setHitCount(1); // 초기 조회수는 1로 설정
            ageGroupProductHitsMapper.insert(hits);
        } else {
            // 기존 데이터가 있으면 조회수를 증가시킴
            hits.setHitCount(hits.getHitCount() + 1);
            ageGroupProductHitsMapper.update(hits);
        }
    }

    // 연령대 계산 로직
    public int calculateAgeGroup(long memberNum) {
        return memberService.calculateAgeGroup(memberNum);
    }

    // 연령대별 상위 3개 상품 조회
    public List<Integer> findTop3ProductsByAgeGroup(int ageGroup) {
        return ageGroupProductHitsMapper.findTop3ByAgeGroup(ageGroup);
    }

}