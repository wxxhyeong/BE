package com.be.recentView.service;

import com.be.recentView.dto.RecentViewedItemDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class RecentViewServiceImpl implements RecentViewService {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<RecentViewedItemDto> getRecentViewItem(Cookie[] cookies) {
        List<RecentViewedItemDto> recentViewedItems = new ArrayList<>();
        String recentProducts = null;

        try {
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("recentViewedItem")) {
                        recentProducts = cookie.getValue();
                        break;
                    }
                }
            }

            if(recentProducts != null) {
                String decodedCookie = URLDecoder.decode(recentProducts, StandardCharsets.UTF_8.toString());
                recentViewedItems = objectMapper.readValue(decodedCookie, new TypeReference<List<RecentViewedItemDto>>() {});
            }
            return recentViewedItems;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public Cookie addRecentViewedItem(Cookie[] cookies, RecentViewedItemDto dto) {
        try{
            List<RecentViewedItemDto> recentViewedItems = getRecentViewItem(cookies);

            for(int i = 0; i < recentViewedItems.size(); i++) {
                if(recentViewedItems.get(i).getProductId() == dto.getProductId()) {
                    recentViewedItems.remove(i);
                }
            }
            recentViewedItems.add(dto);

            // 최근 본 상품 5개만 유지
            if(recentViewedItems.size() > 5) {
                recentViewedItems.remove(0);
            }

            String json = objectMapper.writeValueAsString(recentViewedItems);
            String encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString());

            // 쿠키 업데이트
            Cookie cookie = new Cookie("recentViewedItem", encodedJson);
            cookie.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간 설정 (1일)
            return cookie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cookie resetRecentViewedItem(Cookie[] cookies) {
        try {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("recentViewedItem")) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        return cookie;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
