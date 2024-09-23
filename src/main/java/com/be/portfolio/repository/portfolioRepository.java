package com.be.portfolio.repository;

import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.mapper.PortfolioMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class portfolioRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public portfolioRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<PortfolioItemResDto> getPortfolioItemList(int portfolioId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).getPortfolioItemList(portfolioId);
    }

    public PortfolioResDto getPortfolio(int portfolioId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).getPortfolio(portfolioId);
    }

    public int insertPortfolio(PortfolioReqDto portfolioReqDto) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).insertPortfolio(portfolioReqDto);
    }

    public int insertPortfolioItem(PortfolioItemReqDto portfolioItemReqDto) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).insertPortfolioItem(portfolioItemReqDto);
    }

    public int updatePortfolio(int portfolioId, PortfolioReqDto portfolioReqDto) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).updatePortfolio(portfolioId, portfolioReqDto);
    }

    public int deletePortfolio(int portfolioId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).deletePortfolio(portfolioId);
    }

    public int deletePortfolioItem(int portfolioItemId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).deletePortfolioItem(portfolioItemId);
    }
}
