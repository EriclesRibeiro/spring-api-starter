package com.newshop.dream_shop.application.mapper;

import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.dto.product.ResponseProductDTO;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import org.springframework.stereotype.Component;

/**
* @since 26/10/2025
* Mapper responsável por converter ProductEntity <-> Request/ResponseProduct DTOs.
*/

@Component
public class ProductMapper {

    public ProductEntity toEntity(RequestProductDTO dto) {

        if (dto == null) return null;

        ProductEntity entity = new ProductEntity();

        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setQuantity(dto.quantity());

        return entity;
    }

    public ProductEntity updateEntity(ProductEntity entity, RequestProductDTO dto) {

        if (entity == null || dto == null) return null;

        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setQuantity(dto.quantity());

        return entity;
    }

    public ResponseProductDTO toResponse(ProductEntity entity) {

        if (entity == null) return null;

        return new ResponseProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQuantity()
        );

    }

}
