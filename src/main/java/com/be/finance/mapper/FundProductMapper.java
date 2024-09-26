package com.be.finance.mapper;

import com.be.finance.domain.FundProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundProductMapper {

    void insertFundProduct(FundProductVO fundProductVO);

    List<FundProductVO> getFundProductsList();
}
