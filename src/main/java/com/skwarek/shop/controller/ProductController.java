package com.skwarek.shop.controller;

import com.skwarek.shop.model.product.Product;
import com.skwarek.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        Product product = productService.findById(productId);

        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/products", params = "category")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam("category") String name) {
        List<Product> products = productService.findByCategory(name);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/products", params = "company")
    public ResponseEntity<List<Product>> getAllProductsByCompany(@RequestParam("company") String name) {
        List<Product> products = productService.findByCompany(name);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/products/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product productRequest) {
        Product createdProduct = productService.create(productRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/products")
                .path("/{companyId}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).body(createdProduct);
    }

    @PutMapping(value = "/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
                                                 @RequestBody Product productRequest) {
        Product updatedProduct = productService.update(productId, productRequest);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("productId") Long productId) {
        productService.deleteById(productId);

        return ResponseEntity.noContent().build();
    }

}
