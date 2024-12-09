package com.be.portfolio.service;

import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioPortionDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface PortfolioService {
    PortfolioResDto getPortfolio(int portfolioId);
    List<PortfolioResDto> getPortfolioList(long memberNum);
    List<PortfolioItemResDto> getPortfolioItems(int portfolioId);
    PortfolioResDto createPortfolio(List<Object> portfolioItems, String portfolioName, long memberNum);
    PortfolioResDto updatePortfolio(PortfolioVO portfolioVO);

    @Scheduled(cron = "0 0 0 * * ?")
    void updateAllPortfolio();

    void deletePortfolio(int id);
    PortfolioReqDto calculatePortfolio(PortfolioReqDto portfolioReqDto, List<Object> portfolioItems);
    PortfolioPortionDto calculatePortion(List<PortfolioItemResDto> portfolioItems);
}
