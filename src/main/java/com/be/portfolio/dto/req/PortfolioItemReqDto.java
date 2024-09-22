package com.be.portfolio.dto.req;

import lombok.Data;

@Data
public class PortfolioItemReqDto {
    private int productId;
    private String stockCode;
    private int amount;
    private int expectedReturn;
}
