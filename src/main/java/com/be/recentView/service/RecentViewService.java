package com.be.recentView.service;

import com.be.recentView.dto.RecentViewedItemDto;

import javax.servlet.http.Cookie;
import java.util.List;

public interface RecentViewService {
    List<RecentViewedItemDto> getRecentViewItem(Cookie[] cookies);
    Cookie addRecentViewedItem(Cookie[] cookies, RecentViewedItemDto dto);
    Cookie resetRecentViewedItem(Cookie[] cookies);
}
