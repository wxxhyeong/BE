package com.be.portfolio.service;

import com.be.cart.mapper.CartMapper;
import com.be.finance.mapper.FinanceMapper;
import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.mapper.PortfolioMapper;
import com.be.stock.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    final private PortfolioMapper portfolioMapper;

    @Override
    public PortfolioResDto getPortfolio(int portfolioId) {
        return null;
    }

    @Override
    public List<PortfolioItemResDto> getPortfolioItems(int portfolioId) {
        return List.of();
    }

    @Override
    public PortfolioResDto createPortfolio(List<PortfolioItemReqDto> portfolioItems) {
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
