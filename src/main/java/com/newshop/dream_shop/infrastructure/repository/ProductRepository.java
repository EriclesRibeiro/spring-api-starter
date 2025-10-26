package com.newshop.dream_shop.infrastructure.repository;

import com.newshop.dream_shop.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    List<ProductEntity> findByPriceGreaterThan(BigDecimal price);

}
