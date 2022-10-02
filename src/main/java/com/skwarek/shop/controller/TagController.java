package com.skwarek.shop.controller;

import com.skwarek.shop.model.product.Product;
import com.skwarek.shop.model.product.Tag;
import com.skwarek.shop.service.ProductService;
import com.skwarek.shop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TagController {

    @Autowired
    private ProductService productService;
    @Autowired
    private TagService tagService;

    @GetMapping(value = "/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.findAll();

        if (!tags.isEmpty()) {
            return ResponseEntity.ok(tags);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/tags/{tagId}")
    public ResponseEntity<Tag> getTagById(@PathVariable("tagId") Long tagId) {
        Tag tag = tagService.findById(tagId);

        return ResponseEntity.ok(tag);
    }

    @PostMapping(value = "/products/{productId}/tags")
    public ResponseEntity<Tag> createTag(@PathVariable("productId") Long productId,
                                         @RequestBody Tag tagRequest) {
        Tag createdTag = tagService.create(productId, tagRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/tags")
                .path("/{tagId}").buildAndExpand(createdTag.getId()).toUri();

        return ResponseEntity.created(location).body(createdTag);
    }

    @PutMapping(value = "/tags/{tagId}")
    public ResponseEntity<Tag> updateTag(@PathVariable("tagId") Long tagId,
                                         @RequestBody Tag tagRequest) {
        Tag updatedTag = tagService.update(tagId, tagRequest);

        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping(value = "/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagById(@PathVariable("tagId") Long tagId) {
        tagService.deleteById(tagId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/products/{productId}/tags")
    public ResponseEntity<List<Tag>> getAllTagsOfProductByProductsId(@PathVariable("productId") Long productId) {
        List<Tag> tags = tagService.findTagsByProductsId(productId);

        if (!tags.isEmpty()) {
            return ResponseEntity.ok(tags);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/tags/{tagId}/products")
    public ResponseEntity<List<Product>> getAllProductsOfTagByTagId(@PathVariable("tagId") Long tagId) {
        List<Product> products = productService.findProductsByTagsId(tagId);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "/products/{productId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagOfProductByTagId(@PathVariable("productId") Long productId,
                                                                @PathVariable("tagId") Long tagId) {
        tagService.deleteTagOfProductByTagId(productId, tagId);

        return ResponseEntity.noContent().build();
    }

}
