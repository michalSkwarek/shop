package com.skwarek.shop.service;

import com.skwarek.shop.model.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long productId);

    List<Product> findByCategory(String category);

    List<Product> findByCompany(String company);

    Product create(Product productRequest);

    Product update(Long productId, Product productRequest);

    void deleteById(Long productId);

    List<Product> findProductsByTagsId(Long tagId);

}
