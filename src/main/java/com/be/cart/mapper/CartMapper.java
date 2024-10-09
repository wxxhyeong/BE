package com.be.cart.mapper;

import com.be.cart.domain.CartItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<CartItemVO> getCartItemList(long memberNum);
    int addCartItem(CartItemVO cartVO);
    void deleteCartItem(int cartId);
}
