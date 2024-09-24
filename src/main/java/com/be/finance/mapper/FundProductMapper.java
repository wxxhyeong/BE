package com.be.finance.mapper;

import com.be.finance.domain.FundProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FundProductMapper {

    void insertFundProduct(FundProductVO fundProductVO);
}
