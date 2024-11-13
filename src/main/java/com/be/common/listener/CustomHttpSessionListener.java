package com.be.common.listener;

import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.List;

@Slf4j
@WebListener
public class CustomHttpSessionListener implements HttpSessionListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 세션 생성 시 로직 작성
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Long memberNum = (Long) session.getAttribute("memberNum");
        List<CartItemResDto> cartList = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        CartService cartService = ctx.getBean(CartService.class);

        cartService.updateCartItem(cartList, memberNum);
    }
}
