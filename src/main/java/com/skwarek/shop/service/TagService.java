package com.skwarek.shop.service;

import com.skwarek.shop.model.product.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag findById(Long tagId);

    Tag create(Long productId, Tag tagRequest);

    Tag update(Long tagId, Tag tagRequest);

    void deleteById(Long tagId);

    List<Tag> findTagsByProductsId(Long productId);

    void deleteTagOfProductByTagId(Long productId, Long tagId);

}
