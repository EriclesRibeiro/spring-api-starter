package com.newshop.dream_shop.application.service.product;

import com.newshop.dream_shop.application.dto.PagedResponse;
import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.dto.product.ResponseProductDTO;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService {

    PagedResponse<ResponseProductDTO> findAll(Pageable pageable);
    PagedResponse<ResponseProductDTO> findByName(Pageable pageable, String name);
    PagedResponse<ResponseProductDTO> findByPriceLowerThan(Pageable pageable, BigDecimal price);
    PagedResponse<ResponseProductDTO> findByPriceGreaterThan(Pageable pageable, BigDecimal price);

    ProductEntity save(RequestProductDTO product);
    ProductEntity update(Long id, RequestProductDTO product);
    ProductEntity findById(Long id);

    void delete(Long id);

}
