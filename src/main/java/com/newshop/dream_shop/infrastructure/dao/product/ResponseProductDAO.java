package com.newshop.dream_shop.infrastructure.dao.product;

import java.math.BigDecimal;

public record ResponseProductDAO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) { }
