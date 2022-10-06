package com.skwarek.shop.repository;

import com.skwarek.shop.model.product.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByProductId(Long productId);

    List<Comment> findByCreatedByEmail(String email);

}
