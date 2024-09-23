package com.be.portfolio.service;

import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    final private PortfolioMapper portfolioMapper;

    @Override
    public PortfolioResDto getPortfolio(int portfolioId) {
        return portfolioMapper.getPortfolio(portfolioId);
    }

    @Override
    public List<PortfolioItemResDto> getPortfolioItems(int portfolioId) {
        return portfolioMapper.getPortfolioItemList(portfolioId);
    }

    @Override
    public PortfolioResDto createPortfolio(List<PortfolioItemReqDto> portfolioItems) {
        return null;
    }

    @Override
    public PortfolioResDto updatePortfolio(PortfolioItemReqDto portfolioItemReqDto) {
        return null;
    }

    @Override
    public PortfolioResDto deletePortfolio(int id) {
        return null;
    }

    @Override
    public void calculatePortfolio(PortfolioItemResDto portfolioItemResDto) {

    }

    @Override
    public void calculatePortion(PortfolioItemResDto portfolioItemResDto) {

    }
}
