package com.be.portfolio.service;

import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioPortionDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    final private PortfolioMapper portfolioMapper;

    @Override
    public PortfolioVO getPortfolio(int portfolioId) {
        return portfolioMapper.getPortfolio(portfolioId);
    }

    @Override
    public List<PortfolioItemVO> getPortfolioItems(int portfolioId) {
        return portfolioMapper.getPortfolioItemList(portfolioId);
    }

    @Override
    public PortfolioResDto createPortfolio(PortfolioReqDto reqDto, List<PortfolioItemReqDto> portfolioItems) {
        PortfolioVO vo = reqDto.toVo();
        vo.setPortfolioId(portfolioMapper.insertPortfolio(vo));
        for(PortfolioItemReqDto portfolioItem : portfolioItems) {
            portfolioItem.setPortfolioId(vo.getPortfolioId());
            portfolioMapper.insertPortfolioItem(portfolioItem.toVo());
        }
        vo.setPortfolioItems(getPortfolioItems(vo.getPortfolioId()));

        PortfolioResDto resDto = calculatePortfolio(vo);
        updatePortfolio(resDto.toVo());

        resDto.setPortion(calculatePortion(getPortfolioItems(resDto.getPortfolioId())));

        return resDto;
    }

    @Override
    public PortfolioVO updatePortfolio(PortfolioVO portfolioVO) {
        portfolioMapper.updatePortfolio(portfolioVO.getPortfolioId(), portfolioVO);

        return getPortfolio(portfolioVO.getPortfolioId());
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAllPortfolio() {
        List<Integer> portfolioIds = new ArrayList<>(portfolioMapper.getAllIds());
        for(Integer portfolioId : portfolioIds) {
            PortfolioVO portfolio = getPortfolio(portfolioId);
            List<PortfolioItemVO> portfolioItems = getPortfolioItems(portfolioId);
            portfolio.setPortfolioItems(portfolioItems);
            PortfolioResDto resDto = calculatePortfolio(portfolio);
            updatePortfolio(resDto.toVo());
        }
    }

    @Override
    public PortfolioVO deletePortfolio(int id) {
        PortfolioVO vo = getPortfolio(id);

        // portfolioItems는 delete cascade 설정
        portfolioMapper.deletePortfolio(id);

        return vo;
    }

    @Override
    public PortfolioResDto calculatePortfolio(PortfolioVO vo) {
        PortfolioResDto dto = new PortfolioResDto();
        dto.of(vo);

        // 파이썬 플라스크 서버 연결 + 계산 + 반환
        dto.setTotal(100);
        dto.setExpectedReturn(10000);
        dto.setRiskLevel(14);
        //

        return dto;
    }

    @Override
    public PortfolioPortionDto calculatePortion(List<PortfolioItemVO> portfolioItems) {
        PortfolioPortionDto dto = new PortfolioPortionDto();

        for(PortfolioItemVO item : portfolioItems) {
            switch(item.getProductType()) {
                case "saving" -> dto.setTotalSaving(dto.getTotalSaving() + item.getAmount());
                case "fund" -> dto.setTotalFund(dto.getTotalFund() + item.getAmount());
                case "bond" -> dto.setTotalBond(dto.getTotalBond() + item.getAmount());
                case "stock" -> dto.setTotalStock(dto.getTotalStock() + (item.getAmount() * item.getDailyPrice()));
            }
        }

        return dto;
    }
}
