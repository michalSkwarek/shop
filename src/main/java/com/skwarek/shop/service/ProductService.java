package com.skwarek.shop.service;

import com.skwarek.shop.model.product.Product;
import com.skwarek.shop.model.product.specs.ProductSpecs;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long productId);

    List<Product> findByCategory(String category);

    List<Product> findByCompany(String company);

    Product create(Product productRequest);

    Product update(Long productId, Product productRequest);

    void deleteById(Long productId);

    ProductSpecs findSpecsByProductId(Long productId);

    ProductSpecs createSpecs(Long productId, ProductSpecs productSpecsRequest);

    ProductSpecs updateSpecs(Long productId, ProductSpecs productSpecsRequest);

    void deleteSpecsByProductId(Long productId);

}
