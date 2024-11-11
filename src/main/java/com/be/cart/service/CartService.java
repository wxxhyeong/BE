package com.be.cart.service;

import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.common.event.SessionExpiredEvent;

import java.util.List;

public interface CartService {
    List<CartItemResDto> initCartList(long memberNum);
    List<CartItemResDto> addCartItem(List<CartItemResDto> cartList, CartItemReqDto cartItem);
    List<CartItemResDto> deleteCartItem(List<CartItemResDto> cartList, int cartId);
    void updateCartItem(SessionExpiredEvent event);
}
