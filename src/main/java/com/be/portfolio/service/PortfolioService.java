package com.be.portfolio.service;

import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioPortionDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface PortfolioService {
    PortfolioVO getPortfolio(int portfolioId);
    List<PortfolioItemVO> getPortfolioItems(int portfolioId);
    PortfolioResDto createPortfolio(PortfolioReqDto portfolioReqDto, List<PortfolioItemReqDto> portfolioItems);
    PortfolioVO updatePortfolio(PortfolioVO portfolioVO);

    @Scheduled(cron = "0 0 0 * * ?")
    void updateAllPortfolio();

    PortfolioVO deletePortfolio(int id);
    PortfolioResDto calculatePortfolio(PortfolioVO portfolioVO);
    PortfolioPortionDto calculatePortion(List<PortfolioItemVO> portfolioItems);
}
