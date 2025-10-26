package com.newshop.dream_shop.application.service;

import com.newshop.dream_shop.domain.entity.ProductEntity;
import com.newshop.dream_shop.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll() { return productRepository.findAll(); }

    public List<ProductEntity> findByName(String name) { return productRepository.findByNameContainingIgnoreCase(name); }

    public Optional<ProductEntity> findById(Long id) { return productRepository.findById(id); }

    public ProductEntity save(ProductEntity product) { return productRepository.saveAndFlush(product); }

    public void delete(Long id) { productRepository.deleteById(id); }

    public ProductEntity update(Long id, ProductEntity produto) {

        return productRepository.findById(id)
                .map(entity -> {

                    entity.setName(produto.getName());
                    entity.setDescription(produto.getDescription());
                    entity.setPrice(produto.getPrice());
                    entity.setQuantity(produto.getQuantity());

                    return productRepository.save(entity);

                }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    }
}
