package com.skwarek.shop.controller;

import com.skwarek.shop.model.product.Category;
import com.skwarek.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();

        if (!categories.isEmpty()) {
            return ResponseEntity.ok(categories);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/categories/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.findById(categoryId);

        return ResponseEntity.ok(category);
    }

    @PostMapping(value = "/categories/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category categoryRequest) {
        Category createdCategory = categoryService.create(categoryRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/categories")
                .path("/{categoryId}").buildAndExpand(createdCategory.getId()).toUri();

        return ResponseEntity.created(location).body(createdCategory);
    }

    @PutMapping(value = "/categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                   @RequestBody Category categoryRequest) {
        Category updatedCategory = categoryService.update(categoryId, categoryRequest);

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping(value = "/categories/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteById(categoryId);

        return ResponseEntity.noContent().build();
    }

}
