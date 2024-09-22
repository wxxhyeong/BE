package com.be.portfolio.repository;

import com.be.portfolio.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class portfolioRepository {
    private final PortfolioMapper portfolioMapper;
}
