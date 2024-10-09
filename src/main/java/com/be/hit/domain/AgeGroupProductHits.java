package com.be.hit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeGroupProductHits {
    private Long id;
    private int productId;
    private int ageGroup;
    private int hitCount;
}
