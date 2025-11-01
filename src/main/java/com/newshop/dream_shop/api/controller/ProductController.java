package com.newshop.dream_shop.api.controller;

import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.dto.product.ResponseProductDTO;
import com.newshop.dream_shop.application.mapper.ProductMapper;
import com.newshop.dream_shop.application.service.product.IProductService;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;
    private final ProductMapper productMapper;

    public ProductController(IProductService productService, ProductMapper productMapper) {

        this.productService = productService;
        this.productMapper = productMapper;

    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> findAllProducts() {

        List<ResponseProductDTO> productResponse = productService.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
        return ResponseEntity.ok(productResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> findProductById(@PathVariable Long id) {

        ProductEntity product = productService.findById(id);
        return ResponseEntity.ok().body(productMapper.toResponse(product));

    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseProductDTO>> findProductByName(@RequestParam String name) {

        List<ProductEntity> productEntities = productService.findByName(name);
        List<ResponseProductDTO> products = productEntities
                .stream()
                .map(productMapper::toResponse)
                .toList();

        return ResponseEntity.ok(products);

    }

    @PostMapping
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody @Valid RequestProductDTO product) {

        ProductEntity newProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toResponse(newProduct));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        productService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid RequestProductDTO product) {

        ProductEntity updatedProduct = productService.update(id, product);
        return ResponseEntity.ok(productMapper.toResponse(updatedProduct));

    }

}
