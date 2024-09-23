package com.be.portfolio.service;

import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioResDto;

import java.util.List;

public interface PortfolioService {
    PortfolioResDto getPortfolio(int portfolioId);
    List<PortfolioItemResDto> getPortfolioItems(int portfolioId);
    PortfolioResDto createPortfolio(List<PortfolioItemReqDto> portfolioItems);
    PortfolioResDto updatePortfolio(PortfolioItemReqDto portfolioItemReqDto);
    PortfolioResDto deletePortfolio(int id);
    void calculatePortfolio(PortfolioItemResDto portfolioItemResDto);
    void calculatePortion(PortfolioItemResDto portfolioItemResDto);
}
