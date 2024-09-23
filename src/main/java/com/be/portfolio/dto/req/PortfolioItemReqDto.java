package com.be.portfolio.dto.req;

import com.be.portfolio.domain.PortfolioItemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioItemReqDto {
    private int portfolioId;
    private int productId;
    private String stockCode;
    private int amount;
    private int expectedReturn;

    public PortfolioItemReqDto of(PortfolioItemVO vo) {
        return vo == null ? null : PortfolioItemReqDto.builder()
                .portfolioId(vo.getPortfolioId())
                .productId(vo.getProductId())
                .stockCode(vo.getStockCode())
                .amount(vo.getAmount())
                .expectedReturn(vo.getExpectedReturn())
                .build();
    }


    public PortfolioItemVO toVo() {
        return PortfolioItemVO.builder()
                .portfolioId(portfolioId)
                .productId(productId)
                .stockCode(stockCode)
                .amount(amount)
                .expectedReturn(expectedReturn)
                .build();
    }
}
