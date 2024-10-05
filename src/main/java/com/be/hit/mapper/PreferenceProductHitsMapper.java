package com.be.hit.mapper;

import com.be.hit.domain.PreferenceProductHits;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PreferenceProductHitsMapper {

    // 투자 성향과 상품 ID로 조회수 찾기
    PreferenceProductHits findByPreferenceAndProductId(@Param("preference") int preference, @Param("productId") int productId);

    // 조회수 추가
    void insert(PreferenceProductHits hits);

    // 조회수 업데이트
    void update(PreferenceProductHits hits);

    // 투자 성향별 상위 3개 상품 조회
    List<Integer> findTop3ByPreference(@Param("preference") int preference);
}
