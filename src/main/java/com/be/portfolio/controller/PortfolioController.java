package com.be.portfolio.controller;

import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import com.be.finance.service.FinanceService;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.service.PortfolioService;
import com.be.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Log4j
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final StockService stockService;

    @GetMapping
    public List<PortfolioResDto> getPortfolioList(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            // 테스트용 코드
            List<PortfolioResDto> testList = portfolioService.getPortfolioList(1L);
            session.setAttribute("portfolioList", testList);
            //

            List<PortfolioResDto> portfolioList = (List<PortfolioResDto>) session.getAttribute("portfolioList");
            log.info(portfolioList);
            return portfolioList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니 호출 과정에서 에러 발생");
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<PortfolioResDto> createPortfolio(PortfolioReqDto portfolioReqDto, List<PortfolioItemReqDto> portfolioItems) {
//        JSONObject stockPrices = new JSONObject();
//        List<JSONObject> jsonObjects = new ArrayList<>();
//        for (PortfolioItemReqDto portfolioItemReqDto : portfolioItems) {
//            if(!portfolioItemReqDto.getStockCode().isEmpty()) {
//                JSONObject stockJson = stockService.getStockData(portfolioItemReqDto.getStockCode());
//                jsonObjects.add(stockJson);
//            }
//        }
//
//        for (JSONObject obj : jsonObjects) {
//            Iterator it = obj.keySet().iterator();
//            while (it.hasNext()) {
//                String key = (String)it.next();
//                stockPrices.put(key, obj.get(key));
//            }
//        }

        /* 비동기식 호출 */
        JSONObject stockPrices = new JSONObject();

        // CompletableFuture 리스트 생성
        List<CompletableFuture<JSONObject>> futures = portfolioItems.stream()
                .filter(item -> !item.getStockCode().isEmpty())
                .map(item -> CompletableFuture.supplyAsync(() -> stockService.getStockData(item.getStockCode())))
                .collect(Collectors.toList());

        // CompletableFuture 배열을 모두 완료하고 결과를 합침
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.thenAccept(voidResult -> {
            for (CompletableFuture<JSONObject> future : futures) {
                JSONObject obj = future.join(); // CompletableFuture가 완료될 때까지 대기하고 결과를 가져옴
                if (obj != null) {
                    obj.keySet().forEach(key -> stockPrices.put(key, obj.get(key)));
                }
            }
        }).join(); // CompletableFuture의 완료를 기다림

        return ResponseEntity.ok(portfolioService.createPortfolio(portfolioReqDto, portfolioItems, stockPrices));
    }

    @GetMapping("/{portfolioID}")
    public ResponseEntity<PortfolioResDto> getPortfolio(@PathVariable int portfolioID) {
        return ResponseEntity.ok(portfolioService.getPortfolio(portfolioID));
    }

    @DeleteMapping("/{portfolioID}")
    public ResponseEntity<PortfolioResDto> deletePortfolio(@PathVariable Integer portfolioID) {
        return ResponseEntity.ok(portfolioService.deletePortfolio(portfolioID));
    }
}

