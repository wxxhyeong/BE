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

import static com.be.common.code.SuccessCode.ADD_CART_ITEM;
import static com.be.common.code.SuccessCode.PASSWORD_UPDATED;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Log4j
public class CartController {
    private final CartService cartService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtUtils jwtUtils;

    @GetMapping("/init")
    public ResponseEntity<List<CartItemResDto>> initCartList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CartItemResDto> cartList = cartService.initCartList(jwtUtils.extractMemberNum(request));

        log.info(cartList);

        session.setAttribute("cartList", cartList);
        return ResponseEntity.ok(cartList);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartItemResDto>> getCartList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CartItemResDto> cartList = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        log.info(cartList);

        return ResponseEntity.ok(cartList);
    }

    @PostMapping("/items")
    public ResponseEntity<List<CartItemResDto>> addCartItem(@RequestBody @Valid CartItemReqDto cartItemReqDto, HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info(session.getAttribute("cartList"));
        cartItemReqDto.setMemberNum(jwtUtils.extractMemberNum(request));
        List<CartItemResDto> sessionCartItems = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        List<CartItemResDto> updatedCartList = cartService.addCartItem(sessionCartItems, cartItemReqDto);

        log.info(updatedCartList);

        session.setAttribute("cartList", updatedCartList);

        return ResponseEntity.ok(updatedCartList);
    }

    @DeleteMapping("/items/{productID}")
    public void deleteCartItem(@PathVariable int productID, HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<CartItemResDto> cartList = objectMapper.convertValue(session.getAttribute("cartList"),
                new TypeReference<List<CartItemResDto>>() {});

        session.setAttribute("cartList", cartService.deleteCartItem(cartList, productID));
    }
}

