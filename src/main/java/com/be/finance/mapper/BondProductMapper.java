package com.be.finance.mapper;

import com.be.finance.domain.BondProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BondProductMapper {
    void insertBondProduct(BondProductVO bondProductVO);

    List<BondProductVO> getBondProductsList();
}
