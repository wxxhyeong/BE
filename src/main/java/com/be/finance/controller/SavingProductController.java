package com.be.finance.controller;

import com.be.finance.dto.SavingProductDTO;
import com.be.finance.service.SavingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saving-products")
public class SavingProductController {

    @Autowired
    private SavingProductService savingProductService;

    // 예금 api fetch해서 데이터 저장
    @GetMapping("/fetch-saveDeposit")
    public String fetchDepositProducts() {
        savingProductService.fetchAndSaveDepositProducts();
        return "예금 데이터 저장 완료";
    }

    // 적금 api fetch해서 데이터 저장
    @GetMapping("/fetch-saveSaving")
    public String fetchSavingProducts() {
        savingProductService.fetchAndSaveSavingProducts();
        return "적금 데이터 저장 완료";
    }
}