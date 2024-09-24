package com.be.cart.mapper;

import com.be.cart.domain.CartItemVO;

import java.util.List;

public interface CartMapper {
    List<CartItemVO> getCartItemList(int userNum);
    int addCartItem(CartItemVO cartVO);
    void deleteCartItem(int cartId);
}
