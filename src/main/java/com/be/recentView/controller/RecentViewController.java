package com.be.recentView.controller;

import com.be.recentView.dto.RecentViewedItemDto;
import com.be.recentView.service.RecentViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/recentView")
@RequiredArgsConstructor
public class RecentViewController {
    private final RecentViewService recentViewService;

    @GetMapping("/list")
    public ResponseEntity<List<RecentViewedItemDto>> getRecentViewedItem(HttpServletRequest request) {
        return ResponseEntity.ok(recentViewService.getRecentViewItem(request.getCookies()));
    }

    @PostMapping("/view")
    public void addRecentViewedItem(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid RecentViewedItemDto dto) {
        response.addCookie(recentViewService.addRecentViewedItem(request.getCookies(), dto));
    }

    @DeleteMapping("/reset")
    public void resetRecentViewedItem(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(recentViewService.resetRecentViewedItem(request.getCookies()));
    }
}
