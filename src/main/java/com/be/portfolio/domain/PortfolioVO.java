package com.be.portfolio.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioVO {
    private int portfolioId;
    private String portfolioName;
    private Date creationDate;
    private double total;
    private double expectedReturn;
    private int riskLevel;
    private long memberNum;

    private List<PortfolioItemVO> portfolioItems = new ArrayList<>();
}
