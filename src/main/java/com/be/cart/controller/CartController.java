package com.be.cart.controller;

import com.be.auth.JwtUtils;
import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Log4j
public class CartController {
    private final CartService cartService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtUtils jwtUtils;

    @GetMapping("/init")
    public void initCartList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CartItemResDto> cartList = cartService.initCartList(jwtUtils.extractMemberNum(request));
        session.setAttribute("cartList", cartList);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartItemResDto>> getCartList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CartItemResDto> cartList = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        return ResponseEntity.ok(cartList);
    }

    @PostMapping("/items")
    public void addCartItem(@RequestBody @Valid CartItemReqDto cartItem, HttpServletRequest request) {
        HttpSession session = request.getSession();

        cartItem.setMemberNum(jwtUtils.extractMemberNum(request));
        List<CartItemResDto> cartList = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        session.setAttribute("cartList", cartService.addCartItem(cartList, cartItem));
    }

    @DeleteMapping("/items/{productID}")
    public void deleteCartItem(@PathVariable int productID, HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<CartItemResDto> cartList = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        session.setAttribute("cartList", cartService.deleteCartItem(cartList, productID));
    }
}

