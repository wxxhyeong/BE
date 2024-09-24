package com.be.portfolio.dto.res;

import lombok.Builder;

@Builder
public class PortfolioItemResDto {
    private int portfolioItemId;
    private int portfolioId;
    private int productId;
    private String stockCode;
    private int amount;
    private int expectedReturn;
    private int quantity;
}
