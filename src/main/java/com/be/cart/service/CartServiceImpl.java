package com.be.cart.service;

import com.be.cart.domain.CartItemVO;
import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.mapper.CartMapper;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    @Override
    public List<CartItemResDto> getCartList(int userNum) {
        return cartMapper.getCartItemList(userNum)
                .stream().map(CartItemResDto::of).toList();
    }

    @Override
    public CartItemResDto addCartItem(CartItemReqDto cart) {
        CartItemVO cartVO = cart.toVO();
        CartItemResDto cartResDto = CartItemResDto.of(cartVO);
        cartResDto.setCartId(cartMapper.addCartItem(cartVO));

        return cartResDto;
    }

    @Override
    public void deleteCartItem(int cartId) {
        cartMapper.deleteCartItem(cartId);
    }
}
