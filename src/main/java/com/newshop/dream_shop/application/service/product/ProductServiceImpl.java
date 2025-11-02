package com.newshop.dream_shop.application.service.product;

import com.newshop.dream_shop.application.dto.PagedResponse;
import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.dto.product.ResponseProductDTO;
import com.newshop.dream_shop.application.exception.NotFoundException;
import com.newshop.dream_shop.application.mapper.ProductMapper;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import com.newshop.dream_shop.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public PagedResponse<ResponseProductDTO> findAll(Pageable pageable) {
        Page<ProductEntity> page = productRepository.findAll(pageable);

        // Building a PagedResponse custom
        return PagedResponse.<ResponseProductDTO>builder()
                .items(page.getContent().stream().map(productMapper::toResponse).toList())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public PagedResponse<ResponseProductDTO> findByName(Pageable pageable, String name) {
        Page<ProductEntity> page = productRepository.findByNameContainingIgnoreCase(name);

        return PagedResponse.<ResponseProductDTO>builder()
                .items(page.getContent().stream().map(productMapper::toResponse).toList())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public PagedResponse<ResponseProductDTO> findByPriceLowerThan(Pageable pageable, BigDecimal price) {
        Page<ProductEntity> page = productRepository.findByPriceLowerThan(price);

        return PagedResponse.<ResponseProductDTO>builder()
                .items(page.getContent().stream().map(productMapper::toResponse).toList())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public PagedResponse<ResponseProductDTO> findByPriceGreaterThan(Pageable pageable, BigDecimal price) {
        Page<ProductEntity> page = productRepository.findByPriceGreaterThan(price);

        return PagedResponse.<ResponseProductDTO>builder()
                .items(page.getContent().stream().map(productMapper::toResponse).toList())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public ProductEntity findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    @Override
    public ProductEntity save(RequestProductDTO product) {
        return productRepository.saveAndFlush(productMapper.toEntity(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> { throw new NotFoundException("Produto não encontrado"); });
    }

    @Override
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
