package com.be.finance.controller;

import com.be.finance.dto.SavingProductDTO;
import com.be.finance.service.SavingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/saving-products")
public class SavingProductController {

    @Autowired
    private SavingProductService savingProductService;

    // 예금 api fetch해서 데이터 저장
    @GetMapping("/fetch-save")
    public String fetchSavingProducts() {
        savingProductService.fetchAndSaveSavingProducts();
        return "예/적금 데이터 저장 완료";
    }

}
