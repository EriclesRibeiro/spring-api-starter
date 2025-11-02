package com.newshop.dream_shop.application.service.product;

import com.newshop.dream_shop.application.dto.PagedResponse;
import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.dto.product.ResponseProductDTO;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    PagedResponse<ResponseProductDTO> findAll(Pageable pageable);
    List<ProductEntity> findByName(String name);

    ProductEntity save(RequestProductDTO product);
    ProductEntity update(Long id, RequestProductDTO product);
    ProductEntity findById(Long id);

    void delete(Long id);

}
