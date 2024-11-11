package com.be.common.event;

import com.be.cart.dto.res.CartItemResDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class SessionExpiredEvent extends ApplicationEvent {
    private final Long memberNum;
    private final List<CartItemResDto> cartList;

    public SessionExpiredEvent(Object source, Long memberNum, List<CartItemResDto> cartList) {
        super(source);
        this.memberNum = memberNum;
        this.cartList = cartList;
    }
}
