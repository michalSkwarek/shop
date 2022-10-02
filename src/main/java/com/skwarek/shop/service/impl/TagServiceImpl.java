package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.ProductNotFoundException;
import com.skwarek.shop.exception.TagNotFoundException;
import com.skwarek.shop.model.product.Product;
import com.skwarek.shop.model.product.Tag;
import com.skwarek.shop.repository.ProductRepository;
import com.skwarek.shop.repository.TagRepository;
import com.skwarek.shop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException("Not found tag with id: " + tagId));
    }

    @Override
    public Tag create(Long productId, Tag tagRequest) {
        Optional<Product> productDb = productRepository.findById(productId);

        if (productDb.isPresent()) {
            Optional<Tag> tagDb = tagRepository.findOptionalByName(tagRequest.getName());

            Tag newTag = tagDb.orElseGet(Tag::new);
            newTag.setName(tagRequest.getName());
            productDb.get().addTag(newTag);

            return tagRepository.save(newTag);
        } else {
            throw new ProductNotFoundException("Not found product with id: " + productId);
        }
    }

    @Override
    public Tag update(Long tagId, Tag tagRequest) {
        Optional<Tag> tagDb = tagRepository.findById(tagId);

        if (tagDb.isPresent()) {
            Tag oldTag = tagDb.get();
            oldTag.setName(tagRequest.getName());

            return tagRepository.save(oldTag);
        } else {
            throw new TagNotFoundException("Not found tag with id: " + tagId);
        }
    }

    @Override
    public void deleteById(Long tagId) {
        Optional<Tag> tagDb = tagRepository.findById(tagId);

        if (tagDb.isPresent()) {
            Tag tag = tagDb.get();
            Set<Product> tutorials = new HashSet<>(tag.getProducts());
            tutorials.forEach(t -> t.removeTag(tag));

            tagRepository.deleteById(tagId);
        } else {
            throw new TagNotFoundException("Not found tag with id: " + tagId);
        }
    }

    @Override
    public List<Tag> findTagsByProductsId(Long productId) {
        boolean isProductExists = productRepository.existsById(productId);

        if (isProductExists) {
            return tagRepository.findTagsByProductsId(productId);
        } else {
            throw new ProductNotFoundException("Not found product with id: " + productId);
        }
    }

    @Override
    public void deleteTagOfProductByTagId(Long productId, Long tagId) {
        Optional<Product> productDb = productRepository.findById(productId);

        if (productDb.isPresent()) {
            Product oldProduct = productDb.get();
            Optional<Tag> tag = oldProduct.getTags().stream().filter(t -> t.getId() == tagId).findFirst();
            if (tag.isPresent()) {
                oldProduct.removeTag(tag.get());

                productRepository.save(oldProduct);
            }
        } else {
            throw new ProductNotFoundException("Not found product with id: " + productId);
        }
    }

}
