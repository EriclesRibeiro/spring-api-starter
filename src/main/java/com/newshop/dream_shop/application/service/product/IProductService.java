package com.newshop.dream_shop.application.service.product;

import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.domain.entity.ProductEntity;

import java.util.List;

public interface IProductService {

    List<ProductEntity> findAll();
    List<ProductEntity> findByName(String name);

    ProductEntity save(RequestProductDTO product);
    ProductEntity update(Long id, RequestProductDTO product);
    ProductEntity findById(Long id);

    void delete(Long id);

}
