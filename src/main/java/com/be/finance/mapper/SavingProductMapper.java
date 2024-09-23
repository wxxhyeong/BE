package com.be.finance.mapper;


import com.be.finance.domain.SavingProductRatesVO;
import com.be.finance.domain.SavingProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SavingProductMapper {

    // 예/적금 상품 저장
    void insertSavingProduct(SavingProductVO product);

    // 기간별 수익률 저장
    void insertSavingProductRate(SavingProductRatesVO rate);
}
