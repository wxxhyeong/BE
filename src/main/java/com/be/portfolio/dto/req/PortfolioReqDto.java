package com.be.portfolio.dto.req;

import com.be.portfolio.dto.res.PortfolioItemResDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PortfolioReqDto {
    private Date creationDate;
    private int total;
    private int expectedReturn;
    private int riskLevel;
    private String portfolioName;
    private int userNum;

    private List<PortfolioItemResDto> portfolioItems = new ArrayList<>();
}
