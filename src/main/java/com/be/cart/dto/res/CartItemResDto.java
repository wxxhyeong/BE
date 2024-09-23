package com.be.cart.dto.res;

import lombok.Data;

@Data
public class CartItemResDto {
    private int cartId;
    private int productId;
    private int userNum;
}
