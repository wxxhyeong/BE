package com.be.cart.service;

import com.be.cart.domain.CartItemVO;
import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    @Override
    public List<CartItemResDto> initCartList(long memberNum) {
        return cartMapper.getCartItemList(memberNum)
                .stream().map(CartItemResDto::of).toList();
    }

    @Override
    public List<CartItemResDto> getCartList(long memberNum) {
        return List.of();
    }

    @Override
    public List<CartItemResDto> getSavingsInCart(long memberNum) {
        return List.of();
    }

    @Override
    public List<CartItemResDto> getFundInCart(long memberNum) {
        return List.of();
    }

    @Override
    public List<CartItemResDto> getBondInCart(long memberNum) {
        return List.of();
    }

    @Override
    public List<CartItemResDto> addCartItem(List<CartItemResDto> cartList, CartItemReqDto cartItem) {
        try {
            for(CartItemResDto cartItemResDto : cartList) {
                if(cartItemResDto.getProductId() == cartItem.getProductId()) throw new Exception("중복 아이템 입니다");
            }

            CartItemVO cartVO = cartItem.toVO();
            CartItemResDto cartResDto = CartItemResDto.of(cartVO);
            cartResDto.setCartId(cartMapper.addCartItem(cartVO));

            cartList.add(cartResDto);

            return cartList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니 담기에서 에러 발생");
        }
        return null;
    }

    @Override
    public List<CartItemResDto> deleteCartItem(List<CartItemResDto> cartList, int productId) {
        try {

            for(int i = 0; i < cartList.size(); i++) {
                if(cartList.get(i).getProductId() == productId) cartList.remove(i);
            }

            cartMapper.deleteCartItem(productId);

            return cartList;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니 삭제 과정에서 에러 발생");
        }

        return null;
    }
}
