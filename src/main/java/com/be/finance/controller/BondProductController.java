package com.be.finance.controller;


import com.be.finance.service.BondProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bond")
public class BondProductController {

    @Autowired
    private BondProductService bondProductService;

    // 채권 데이터 DB 저장
    @GetMapping("/fetch-save")
    @ResponseBody
    public String fetchAndSaveBondProducts() {
        try {
            bondProductService.fetchAndSaveBondProducts();
            return "채권 데이터 저장 성공"; // 성공
        } catch (Exception e) {
            e.printStackTrace();
            return "채권 데이터 저장 중 오류 발생"; // 오류
        }
    }
}
