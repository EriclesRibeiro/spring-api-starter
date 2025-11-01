package com.newshop.dream_shop.infrastructure.repository;

import com.newshop.dream_shop.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    List<ProductEntity> findByPriceGreaterThan(BigDecimal price);

}
