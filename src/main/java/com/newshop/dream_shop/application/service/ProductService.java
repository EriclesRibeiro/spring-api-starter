package com.newshop.dream_shop.application.service;

import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.mapper.ProductMapper;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import com.newshop.dream_shop.infrastructure.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.productMapper= productMapper;

    }

    public List<ProductEntity> findAll() { return productRepository.findAll(); }

    public List<ProductEntity> findByName(String name) { return productRepository.findByNameContainingIgnoreCase(name); }

    public Optional<ProductEntity> findById(Long id) { return productRepository.findById(id); }

    public ProductEntity save(RequestProductDTO product) { return productRepository.saveAndFlush(productMapper.toEntity(product)); }

    public void delete(Long id) { productRepository.deleteById(id); }

    public ProductEntity update(Long id, RequestProductDTO product) {

        return productRepository.findById(id)
                .map(entity -> {

                    entity.setName(product.name());
                    entity.setDescription(product.description());
                    entity.setPrice(product.price());
                    entity.setQuantity(product.quantity());

                    return productRepository.save(entity);

                }).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

    }
}
