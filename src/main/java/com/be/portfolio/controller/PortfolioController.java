package com.be.portfolio.controller;

import com.be.auth.JwtUtils;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Log4j
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final JwtUtils jwtUtils;

    @GetMapping("/list")
    public ResponseEntity<List<PortfolioResDto>> getPortfolioList(HttpServletRequest request) {
        return ResponseEntity.ok(portfolioService.getPortfolioList(jwtUtils.extractMemberNum(request)));
    }

    @GetMapping("/details/{portfolioId}")
    public ResponseEntity<PortfolioResDto> getPortfolio(@PathVariable int portfolioId) {
        return ResponseEntity.ok(portfolioService.getPortfolio(portfolioId));
    }

    @PostMapping
    public ResponseEntity<PortfolioResDto> createPortfolio(@RequestBody @Valid PortfolioReqDto portfolioReqDto, HttpServletRequest request) {
        return ResponseEntity.ok(portfolioService.createPortfolio(portfolioReqDto, jwtUtils.extractMemberNum(request)));
    }

    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Integer portfolioId) {
        portfolioService.deletePortfolio(portfolioId);
        return ResponseEntity.noContent().build();
    }
}

