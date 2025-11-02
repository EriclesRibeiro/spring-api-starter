package com.newshop.dream_shop.api.controller;

import com.newshop.dream_shop.application.dto.PagedResponse;
import com.newshop.dream_shop.application.dto.product.RequestProductDTO;
import com.newshop.dream_shop.application.dto.product.ResponseProductDTO;
import com.newshop.dream_shop.application.dto.Response;
import com.newshop.dream_shop.application.mapper.ProductMapper;
import com.newshop.dream_shop.application.service.product.ProductService;
import com.newshop.dream_shop.domain.entity.ProductEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {

        this.productService = productService;
        this.productMapper = productMapper;

    }

    @GetMapping
    public ResponseEntity<Response<PagedResponse<ResponseProductDTO>>> findAllProducts(Pageable pageable) {

        PagedResponse<ResponseProductDTO> productResponse = productService.findAll(pageable);
        return ResponseEntity.ok(Response.success(productResponse));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ResponseProductDTO>> findProductById(@PathVariable Long id) {

        ProductEntity product = productService.findById(id);
        return ResponseEntity.ok().body(Response.success(productMapper.toResponse(product)));

    }

    @GetMapping("/search")
    public ResponseEntity<Response<List<ResponseProductDTO>>> findProductByName(@RequestParam String name) {

        List<ProductEntity> productEntities = productService.findByName(name);
        List<ResponseProductDTO> products = productEntities
                .stream()
                .map(productMapper::toResponse)
                .toList();

        return ResponseEntity.ok(Response.success(products));

    }

    @PostMapping
    public ResponseEntity<Response<ResponseProductDTO>> createProduct(@RequestBody @Valid RequestProductDTO product) {

        ProductEntity newProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.success(productMapper.toResponse(newProduct)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteProduct(@PathVariable Long id) {

        productService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ResponseProductDTO>> updateProduct(@PathVariable Long id, @RequestBody @Valid RequestProductDTO product) {

        ProductEntity updatedProduct = productService.update(id, product);
        return ResponseEntity.ok(Response.success(productMapper.toResponse(updatedProduct)));

    }

}
