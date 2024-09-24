package com.be.cart.domain;

import lombok.Data;

@Data
public class CartItemVO {
    private int cartId;
    private int productId;
    private int userNum;
}
