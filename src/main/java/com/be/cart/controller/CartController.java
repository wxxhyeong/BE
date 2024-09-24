package com.be.cart.controller;


import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userNum}")
    public ResponseEntity<List<CartItemResDto>> getCartItems(@PathVariable int userNum) {
        return ResponseEntity.ok(cartService.getCartList(userNum));
    }

    @PostMapping
    public ResponseEntity<CartItemResDto> addCartItem(CartItemReqDto cartItem) {
        return ResponseEntity.ok(cartService.addCartItem(cartItem));
    }

    @DeleteMapping("/{cartID}")
    public void deleteCartItem(@PathVariable int cartID) {
        cartService.deleteCartItem(cartID);
    }
}

