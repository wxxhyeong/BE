package com.be.portfolio.dto.req;

import com.be.portfolio.domain.PortfolioVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioReqDto {
    private Date creationDate;
    private int total;
    private int expectedReturn;
    private int riskLevel;
    private String portfolioName;
    private int userNum;

    private List<PortfolioItemReqDto> portfolioItems = new ArrayList<>();

    public PortfolioReqDto of(PortfolioVO vo) {
        return vo == null ? null : PortfolioReqDto.builder()
                .creationDate(vo.getCreationDate())
                .total(vo.getTotal())
                .expectedReturn(vo.getExpectedReturn())
                .riskLevel(vo.getRiskLevel())
                .portfolioName(vo.getPortfolioName())
                .userNum(vo.getUserNum())
                .build();
    }


    public PortfolioVO toVo() {
        return PortfolioVO.builder()
                .creationDate(creationDate)
                .total(total)
                .expectedReturn(expectedReturn)
                .riskLevel(riskLevel)
                .portfolioName(portfolioName)
                .userNum(userNum)
                .build();
    }
}
