package com.newshop.dream_shop.infrastructure.dao.product;

import java.math.BigDecimal;

public record RequestProductDAO(
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) { }
