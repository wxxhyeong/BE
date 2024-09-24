package com.be.cart.dto.res;

import com.be.cart.domain.CartItemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResDto {
    private int cartId;
    private int productId;
    private int userNum;

    public static CartItemResDto of(CartItemVO cartVO) {
        return null;
    }
}
