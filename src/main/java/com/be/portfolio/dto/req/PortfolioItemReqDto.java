package com.be.portfolio.dto.req;

import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.dto.res.PortfolioItemResDto;
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
    private Integer productId;
    private String stockCode;
    private double amount;
    private double expectedReturn;
    private int riskLevel;
    private Character productType;

    public static PortfolioItemReqDto of(PortfolioItemResDto resDto) {
        return resDto == null ? null : PortfolioItemReqDto.builder()
                .portfolioId(resDto.getPortfolioId())
                .productId(resDto.getProductId())
                .stockCode(resDto.getStockCode())
                .amount(resDto.getAmount())
                .expectedReturn(resDto.getExpectedReturn())
                .riskLevel(resDto.getRiskLevel())
                .productType(resDto.getProductType())
                .build();
    }

    public PortfolioItemVO toVo() {
        return PortfolioItemVO.builder()
                .portfolioId(portfolioId)
                .productId(productId)
                .stockCode(stockCode)
                .amount(amount)
                .expectedReturn(expectedReturn)
                .riskLevel(riskLevel)
                .productType(productType)
                .build();
    }
}
