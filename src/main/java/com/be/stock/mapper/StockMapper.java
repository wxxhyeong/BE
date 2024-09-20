package com.be.stock.mapper;


import com.be.stock.domain.StockVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper {
    int insert(StockVO stockVO);
}
