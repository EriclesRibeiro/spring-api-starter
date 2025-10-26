package com.newshop.dream_shop.infrastructure.dto.product;

import java.math.BigDecimal;

public record RequestProductDTO(
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) { }
