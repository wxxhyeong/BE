package com.be.stock.service;

import com.be.stock.domain.StockVO;
import com.be.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
        System.out.println("StockService created");
    }

    public void insert() {
        StockParser parser = new StockParser();
        List<StockVO> list = parser.parse();
        int index=1;
        for(StockVO stockVO : list){
            stockRepository.insert(stockVO);
            System.out.println(index+">");
            index++;
        }
    }
}
