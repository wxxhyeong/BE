package com.be.hit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceProductHits {
    private int id;
    private int productId;
    private int preference;
    private int hit;
}
