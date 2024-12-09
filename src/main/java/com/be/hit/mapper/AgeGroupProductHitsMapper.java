package com.be.hit.mapper;

import com.be.hit.domain.AgeGroupProductHits;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgeGroupProductHitsMapper {
    // 상품 ID와 연령대에 따른 조회수 레코드 조회
    AgeGroupProductHits findByAgeGroupAndProductId(@Param("ageGroup") int ageGroup, @Param("productId") int productId);

    // 새로운 조회수 레코드 추가
    void insert(AgeGroupProductHits hits);

    // 기존 조회수 레코드 업데이트
    void update(AgeGroupProductHits hits);

    // 연령대별 상위 3개의 상품 ID 조회
    List<Integer> findTop3ByAgeGroup(@Param("ageGroup") int ageGroup);
}