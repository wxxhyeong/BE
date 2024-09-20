package com.be.stock.repository;

import com.be.stock.domain.StockVO;
import com.be.stock.mapper.StockMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository {
    private SqlSessionTemplate my;
    public StockRepository() {System.out.println("StockRepository created");}

    @Autowired
    public StockRepository(SqlSessionTemplate my) {this.my = my;}

    public int insert(StockVO stockVO){return my.getMapper(StockMapper.class).insert(stockVO);}
}
