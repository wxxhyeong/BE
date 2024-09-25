package com.be.stock.controller;

import com.be.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    // 주식 데이터를 DB에 저장하는 fetch 메서드 (GET /api/stock/fetch)
    @GetMapping("/fetch")
    public String fetchStockData() {
        stockService.fetchStock();  // 주식 데이터를 가져와서 DB에 저장
        return "fetchResult";  // fetchResult.jsp 파일로 이동
    }
}
