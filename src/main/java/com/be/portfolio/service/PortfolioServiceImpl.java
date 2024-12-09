package com.be.portfolio.service;

import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioPortionDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j
public class PortfolioServiceImpl implements PortfolioService {
    final private PortfolioMapper portfolioMapper;

    @Override
    public List<PortfolioResDto> getPortfolioList(long memberNum) {
        return portfolioMapper.getPortfolioList(memberNum)
                .stream().map(PortfolioResDto::of).toList();
    }

    @Override
    public PortfolioResDto getPortfolio(int portfolioId) {
        PortfolioResDto portfolio = PortfolioResDto.of(portfolioMapper.getPortfolio(portfolioId));
        portfolio.setPortfolioItems(getPortfolioItems(portfolioId));
        portfolio.setPortion(calculatePortion(portfolio.getPortfolioItems()));

        return Optional.of(portfolio)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<PortfolioItemResDto> getPortfolioItems(int portfolioId) {
        return portfolioMapper.getPortfolioItemList(portfolioId)
                .stream().map(PortfolioItemResDto::of).toList();
    }

    @Override
    public PortfolioResDto createPortfolio(List<Object> portfolioItems, String portfolioName, long memberNum) {
        log.info("service portfolio create");
        PortfolioReqDto portfolio = new PortfolioReqDto();
        portfolio.setMemberNum(memberNum);
        portfolio.setPortfolioName(portfolioName);
        portfolio = calculatePortfolio(portfolio, portfolioItems);

        PortfolioVO vo = portfolio.toVo();
        portfolioMapper.insertPortfolio(vo);
        int portfolioId = vo.getPortfolioId();
        log.info("@@@@@@@@" + String.valueOf(portfolioId));
        for(PortfolioItemReqDto portfolioItem : portfolio.getPortfolioItems()) {
            portfolioItem.setPortfolioId(portfolioId);
            portfolioMapper.insertPortfolioItem(portfolioItem.toVo());
        }

        PortfolioResDto resDto = PortfolioResDto.of(vo);
        resDto.setPortfolioItems(getPortfolioItems(portfolioId));
        resDto.setPortion(calculatePortion(resDto.getPortfolioItems()));
        log.info(resDto.toString());

        return resDto;
    }

    @Override
    public PortfolioResDto updatePortfolio(PortfolioVO portfolioVO) {
        portfolioMapper.updatePortfolio(portfolioVO);

        return getPortfolio(portfolioVO.getPortfolioId());
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAllPortfolio() {
        List<Integer> portfolioIds = new ArrayList<>(portfolioMapper.getAllIds());
        for(Integer portfolioId : portfolioIds) {
            PortfolioReqDto portfolio = PortfolioReqDto.of(getPortfolio(portfolioId));
            List<PortfolioItemReqDto> portfolioItems = getPortfolioItems(portfolioId)
                    .stream().map(PortfolioItemReqDto::of).toList();
            portfolio.setPortfolioItems(portfolioItems);
//            PortfolioReqDto reqDto = calculatePortfolio(portfolio);
//            updatePortfolio(reqDto.toVo());
        }
    }

    @Override
    public void deletePortfolio(int id) {
        // portfolioItems는 delete cascade 설정
        portfolioMapper.deletePortfolio(id);
    }

    @Override
    public PortfolioReqDto calculatePortfolio(PortfolioReqDto portfolio, List<Object> portfolioItems) {
        // 파이썬 플라스크 서버 연결 + 계산 + 데이터 반환
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/api/portfolio/calculate";
        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(portfolioItems);

        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                System.out.println("Response: " + responseEntity.getBody());
            } else {
                throw new Exception("계산 실패!!! " + responseEntity.getStatusCode());
            }

            // portfolio의 expectedReturn, riskLevel, total, portfolioItem의 expectedReturn, riskLevel, amount을  Map에 반환
            Map<String, Object> data = responseEntity.getBody();

            double portfolioExpectedReturn = (Double) data.get("expectedReturn");
            int portfolioRiskLevel = (Integer) data.get("riskLevel");
            double total = (Double) data.get("total");

            portfolio.setTotal(total);
            portfolio.setExpectedReturn(portfolioExpectedReturn);
            portfolio.setRiskLevel(portfolioRiskLevel);

            List<Map<String, Object>> tempList = (List<Map<String, Object>>) data.get("portfolioItems");
            List<PortfolioItemReqDto> portfolioItemList = new ArrayList<>();
            for(Map item : tempList) {
                PortfolioItemReqDto portfolioItem = new PortfolioItemReqDto();
                portfolioItem.setAmount((Double) item.get("amount"));
                portfolioItem.setExpectedReturn((Double) item.get("expectedReturn"));
                portfolioItem.setRiskLevel((Integer) item.get("riskLevel"));
                if(item.get("productId") == null) {
                    portfolioItem.setStockCode((String) item.get("stockCode"));
                    portfolioItem.setProductType(null);
                } else {
                    portfolioItem.setProductId((Integer) item.get("productId"));
                    portfolioItem.setProductType(((String)item.get("productType")).charAt(0));
                }
                portfolioItemList.add(portfolioItem);
            }

            portfolio.setPortfolioItems(portfolioItemList);

            log.info(portfolio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return portfolio;
    }

    @Override
    public PortfolioPortionDto calculatePortion(List<PortfolioItemResDto> portfolioItems) {
        PortfolioPortionDto dto = new PortfolioPortionDto();

        try {
            for(PortfolioItemResDto item : portfolioItems) {
                if (item.getProductType() == null) {
                    dto.setTotalStock(dto.getTotalStock() + item.getAmount());
                } else {
                    switch(item.getProductType()) {
                        case 'S' -> dto.setTotalSaving(dto.getTotalSaving() + item.getAmount());
                        case 'F' -> dto.setTotalFund(dto.getTotalFund() + item.getAmount());
                        case 'B' -> dto.setTotalBond(dto.getTotalBond() + item.getAmount());
                        default -> throw new Exception("타입을 특정할 수 없습니다.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
