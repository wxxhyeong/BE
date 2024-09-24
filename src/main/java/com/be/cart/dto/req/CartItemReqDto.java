package com.be.cart.dto.req;

import com.be.cart.domain.CartItemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemReqDto {
    private int productId;
    private int userNum;

    public CartItemVO toVO() {
        return null;
    }
}
