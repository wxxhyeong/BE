package com.be.portfolio.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioItemVO {
    private int portfolioItemId;
    public int portfolioId;
    private Integer productId;
    private String stockCode;
    private double amount;
    private double expectedReturn;
    private int riskLevel;
    private char productType;
}
