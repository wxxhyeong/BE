package com.be.recentView.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/api/recentView")
@RequiredArgsConstructor
public class RecentViewController {
    @GetMapping
    public List<String> getRecentViewedItems(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<String> productIds = new ArrayList<>();
        StringBuilder recentProducts = new StringBuilder();

        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("recentViewedItems")) {
                    recentProducts.append(cookie.getValue());
                    break;
                }
            }
            // 뒤에서부터 최근에 본 목록
            productIds = Arrays.asList(recentProducts.toString().split(","));
        }

        return productIds;
    }

    @GetMapping("/do") /* -> 쿠키 생성(상품 조회시 ~ financeController로 이동) */
    public void getRecentViewedItem(HttpServletRequest request, HttpServletResponse response, String productId) {
//        Cookie cookie = new Cookie("recentViewedItem" + Id, String.valueOf(productId));
//        cookie.setPath("/");
//        cookie.setMaxAge(60 * 60 * 24);
//        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();
        StringBuilder recentProducts = new StringBuilder();

        if(cookies == null) {
            Cookie cookie = new Cookie("recentViewedItems", productId);
            return;
        } else {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("recentViewedItems")) {
                    recentProducts.append(cookie.getValue());
                    break;
                }
            }
            if(recentProducts.isEmpty()) {
                Cookie cookie = new Cookie("recentViewedItems", productId);
                return;
            }
        }

        List<String> productIds = Arrays.asList(recentProducts.toString().split(","));
        // 최근 본 상품 5개만 유지
        if(productIds.size() > 5) {
            productIds.remove(0);
        }

        // 쿠키 업데이트
        Cookie recentProductsCookie = new Cookie("recentProducts", String.join(",", productIds));
        recentProductsCookie.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간 설정 (1일)
        response.addCookie(recentProductsCookie);
    }
}
