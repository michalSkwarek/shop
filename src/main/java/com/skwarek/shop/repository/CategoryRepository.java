package com.skwarek.shop.repository;

import com.skwarek.shop.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    Optional<Category> findOptionalByName(String name);

}
