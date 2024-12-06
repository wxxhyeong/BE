package com.be.hit.service;

import com.be.hit.domain.PreferenceProductHits;
import com.be.hit.mapper.PreferenceProductHitsMapper;
import com.be.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferenceProductHitsService {

    private final PreferenceProductHitsMapper preferenceProductHitsMapper;
    private final MemberMapper memberMapper;

    // 투자성향에 따른 조회수 증가
    public void increaseProductHit(int preference, int productId) {
        PreferenceProductHits hits = preferenceProductHitsMapper.findByPreferenceAndProductId(preference, productId);

        if (hits == null) {
            // 해당 데이터가 없으면 새로운 조회수 레코드를 추가
            hits = new PreferenceProductHits();
            hits.setPreference(preference);
            hits.setProductId(productId);
            hits.setHit(1);
            preferenceProductHitsMapper.insert(hits);
        } else {
            // 기존 데이터가 있으면 조회수를 증가
            hits.setHit(hits.getHit() + 1);
            preferenceProductHitsMapper.update(hits);
        }
    }

    // 투자 성향별 상위 3개 상품 조회
    public List<Integer> findTop3ProductsByPreference(int preference) {
        return preferenceProductHitsMapper.findTop3ByPreference(preference);
    }

    // 사용자별 투자성향 조회 (Member 서비스로부터 가져옴)
    public int getUserPreference(long memberNum) {
        return memberMapper.findPreferenceByMemberNum(memberNum);
    }
}
