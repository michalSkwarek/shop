package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.CategoryDuplicateException;
import com.skwarek.shop.exception.CategoryNotFoundException;
import com.skwarek.shop.model.product.Category;
import com.skwarek.shop.repository.CategoryRepository;
import com.skwarek.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Not found category with id: " + categoryId));
    }

    @Override
    public Category create(Category categoryRequest) {
        boolean isCategoryExists = categoryRepository.existsByName(categoryRequest.getName());

        if (!isCategoryExists) {
            Category newCategory = new Category();
            newCategory.setName(categoryRequest.getName());
            newCategory.setDescription(categoryRequest.getDescription());

            return categoryRepository.save(newCategory);
        } else {
            throw new CategoryDuplicateException("Duplicate category with name: " + categoryRequest.getName());
        }
    }

    @Override
    public Category update(Long categoryId, Category categoryRequest) {
        Optional<Category> categoryDb = categoryRepository.findById(categoryId);

        if (categoryDb.isPresent()) {
            Category oldCategory = categoryDb.get();
            oldCategory.setName(categoryRequest.getName());
            oldCategory.setDescription(categoryRequest.getDescription());

            return categoryRepository.save(oldCategory);
        } else {
            throw new CategoryNotFoundException("Not found category with id: " + categoryId);
        }
    }

    @Override
    public void deleteById(Long categoryId) {
        boolean isCategoryExists = categoryRepository.existsById(categoryId);

        if (isCategoryExists) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new CategoryNotFoundException("Not found category with id: " + categoryId);
        }
    }

}
