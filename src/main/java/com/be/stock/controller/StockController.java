package com.be.stock.controller;


import com.be.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;
    public StockController(StockService stockService) {System.out.println("StockController");}

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
    @GetMapping
    public String stock() {return "stock/stock";}

    @GetMapping("/insert")
    public String insert() {
        stockService.insert();
        return "stock/insert";
    }
}