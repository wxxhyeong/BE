package com.be.common.listener;

import com.be.cart.dto.res.CartItemResDto;
import com.be.common.event.SessionExpiredEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@Log4j2
@WebListener
public class CustomHttpSessionListener implements HttpSessionListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("sessionCreated 호출됨");
        // 세션 생성 시 로직 작성
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("sessionDestroyed");
        HttpSession session = se.getSession();

        Long memberNum = (Long) session.getAttribute("memberNum");
        List<CartItemResDto> cartList = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        if (eventPublisher == null) {
            this.eventPublisher = (ApplicationEventPublisher) se.getSession().getServletContext().getAttribute("applicationContext");
        }

        if (eventPublisher != null) {
            eventPublisher.publishEvent(new SessionExpiredEvent(this, memberNum, cartList));
        } else {
            log.warn("이벤트 발생 불가!");
        }
    }
}
