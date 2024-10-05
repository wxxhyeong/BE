package com.be.hit.service;

import com.be.finance.domain.*;
import com.be.finance.mapper.BondProductMapper;
import com.be.finance.mapper.FundProductMapper;
import com.be.finance.mapper.ProductMapper;
import com.be.finance.mapper.SavingProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopFinanceService {

    @Autowired
    private BondProductMapper bondProductMapper;

    @Autowired
    private FundProductMapper fundProductMapper;

    @Autowired
    private SavingProductMapper savingProductMapper;

    @Autowired
    private ProductMapper productMapper;

    // 연령대별 상위 3개 상품의 상세 정보를 가져오는 메소드
    public List<Object> getTop3ProductsByAgeGroup(int ageGroup, List<Integer> productIds) {
        List<Object> topProducts = new ArrayList<>();

        for (Integer productId : productIds) {
            ProductVO product = productMapper.findById(productId);

            char productType = product.getProductType();

            if (productType == 'B') {
                BondProductVO bondProduct = bondProductMapper.getBondProductDetail(productId);
                topProducts.add(bondProduct);
            } else if (productType == 'S') {
                SavingProductVO savingProduct = savingProductMapper.findByProductId(productId);
                List<SavingProductRatesVO> savingProductRates = savingProductMapper.getSavingProductRatesDetail(productId);
                Map<String, Object> savingProductData = new HashMap<>();
                savingProductData.put("savingProduct", savingProduct);
                savingProductData.put("savingProductRates", savingProductRates);
                topProducts.add(savingProductData);
            } else if (productType == 'F') {
                FundProductVO fundProduct = fundProductMapper.getFundProductDetail(productId);
                topProducts.add(fundProduct);
            }
        }

        return topProducts;
    }

    // 연령대별 상위 3개 상품의 상세 정보를 가져오는 메소드
    public List<Object> getTop3ProductsByPreference(int preference, List<Integer> productIds) {
        List<Object> topProducts = new ArrayList<>();

        for (Integer productId : productIds) {
            ProductVO product = productMapper.findById(productId);

            char productType = product.getProductType();

            if (productType == 'B') {
                BondProductVO bondProduct = bondProductMapper.getBondProductDetail(productId);
                topProducts.add(bondProduct);
            } else if (productType == 'S') {
                SavingProductVO savingProduct = savingProductMapper.findByProductId(productId);
                List<SavingProductRatesVO> savingProductRates = savingProductMapper.getSavingProductRatesDetail(productId);
                Map<String, Object> savingProductData = new HashMap<>();
                savingProductData.put("savingProduct", savingProduct);
                savingProductData.put("savingProductRates", savingProductRates);
                topProducts.add(savingProductData);
            } else if (productType == 'F') {
                FundProductVO fundProduct = fundProductMapper.getFundProductDetail(productId);
                topProducts.add(fundProduct);
            }
        }

        return topProducts;
    }
}
