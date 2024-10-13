package com.be.cart.service;

import com.be.cart.domain.CartItemVO;
import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.mapper.CartMapper;
import com.be.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.be.common.code.ErrorCode.EXISTING_CART_ITEM;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    @Override
    public List<CartItemResDto> initCartList(long memberNum) {
        return cartMapper.getCartItemList(memberNum)
                .stream().map(CartItemResDto::of).toList();
    }

    @Override
    public List<CartItemResDto> addCartItem(List<CartItemResDto> sessionCartItems, CartItemReqDto cartItemReqDto) {

        for (CartItemResDto sessionCartItem : sessionCartItems) {
            if (sessionCartItem.getProductId() == cartItemReqDto.getProductId()) {
                throw new CustomException(EXISTING_CART_ITEM);
            }
        }

        CartItemVO cartVO = cartItemReqDto.toVO();
        cartMapper.addCartItem(cartVO);
        CartItemResDto cartResDto = CartItemResDto.of(cartVO);

        sessionCartItems.add(cartResDto);

        return sessionCartItems;
    }

    @Override
    public List<CartItemResDto> deleteCartItem(List<CartItemResDto> cartList, int cartId) {
        try {

            for(int i = 0; i < cartList.size(); i++) {
                if(cartList.get(i).getCartId() == cartId) cartList.remove(i);
            }

            cartMapper.deleteCartItem(cartId);

            return cartList;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니 삭제 과정에서 에러 발생");
        }

        return cartList;
    }
}
