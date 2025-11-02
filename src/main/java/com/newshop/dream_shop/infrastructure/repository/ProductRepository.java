package com.newshop.dream_shop.infrastructure.repository;

import com.newshop.dream_shop.domain.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findByNameContainingIgnoreCase(String name);
    Page<ProductEntity> findByPriceLowerThan(BigDecimal price);
    Page<ProductEntity> findByPriceGreaterThan(BigDecimal price);

}
