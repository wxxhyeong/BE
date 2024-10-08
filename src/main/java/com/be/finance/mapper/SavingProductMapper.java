package com.be.finance.mapper;


import com.be.finance.domain.SavingProductRatesVO;
import com.be.finance.domain.SavingProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SavingProductMapper {

    // 예/적금 상품 저장
    void insertSavingProduct(SavingProductVO product);

    // 기간별 수익률 저장
    void insertSavingProductRate(SavingProductRatesVO rate);

    // 예금 데이터 리스트 조회
    List<SavingProductVO> getDepositProducts();

    // 예금 기간별 수익률 리스트 조회
    List<SavingProductRatesVO> getDepositRates();

    // 적금 데이터 리스트 조회
    List<SavingProductVO> getSavingProducts();

    // 적금 기간별 수익률 리스트 조회
    List<SavingProductRatesVO> getSavingRates();
}
