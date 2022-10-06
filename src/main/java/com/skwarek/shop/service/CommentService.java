package com.skwarek.shop.service;

import com.skwarek.shop.model.product.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(Long commentId);

    Comment create(Long productId, Comment commentRequest);

    Comment update(Long commentId, Comment commentRequest);

    void deleteById(Long commentId);

    List<Comment> findByProductId(Long productId);

    List<Comment> findByEmail(String email);

}
