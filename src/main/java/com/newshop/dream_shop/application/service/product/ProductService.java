package com.newshop.dream_shop.application.service.product;

import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.exception.NotFoundException;
import com.newshop.dream_shop.application.mapper.ProductMapper;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import com.newshop.dream_shop.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public ProductEntity findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    public ProductEntity save(RequestProductDTO product) {
        return productRepository.saveAndFlush(productMapper.toEntity(product));
    }

    public void delete(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> { throw new NotFoundException("Produto não encontrado"); });
    }

    public ProductEntity update(Long id, RequestProductDTO product) {
        return productRepository.findById(id)
                .map(entity -> {

                    entity.setName(product.name());
                    entity.setDescription(product.description());
                    entity.setPrice(product.price());
                    entity.setQuantity(product.quantity());

                    return productRepository.save(entity);

                }).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }
}
