package com.be.hit.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitRequestDto {
    private int ageGroup;
    private int productId;
    private int preference;
}