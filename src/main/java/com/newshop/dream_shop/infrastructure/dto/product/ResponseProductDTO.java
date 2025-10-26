package com.newshop.dream_shop.infrastructure.dto.product;

import java.math.BigDecimal;

public record ResponseProductDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) { }
