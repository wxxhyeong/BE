package com.be.cart.mapper;

import com.be.cart.domain.CartDataVO;
import com.be.cart.domain.CartItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<CartItemVO> getCartItemList(long memberNum);
    List<CartDataVO> getCartDataList(long memberNum);
    int addCartItem(CartDataVO cartVO);
    void deleteCartItem(int cartId);
}
