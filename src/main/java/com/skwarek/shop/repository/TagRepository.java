package com.skwarek.shop.repository;

import com.skwarek.shop.model.product.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findTagsByProductsId(Long productId);

    Optional<Tag> findOptionalByName(String name);

}
