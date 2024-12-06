package com.be.portfolio.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioPortionDto {
    private double totalSaving;
    private double totalBond;
    private double totalFund;
    private double totalStock;
}
