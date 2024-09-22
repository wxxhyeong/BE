package com.be.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioItemVO {
    private int portfolioItemId;
    private int portfolioId;
    private int productId;
    private String stockCode;
    private int amount;
    private int expectedReturn;
}
