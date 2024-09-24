package com.be.finance.mapper;

import com.be.finance.domain.BondProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BondProductMapper {
    void insertBondProduct(BondProductVO bondProductVO);
}
