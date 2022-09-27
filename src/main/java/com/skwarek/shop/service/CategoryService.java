package com.skwarek.shop.service;

import com.skwarek.shop.model.product.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long categoryId);

    Category create(Category categoryRequest);

    Category update(Long categoryId, Category categoryRequest);

    void deleteById(Long categoryId);

}
