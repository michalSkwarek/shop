package com.skwarek.shop.repository;

import com.skwarek.shop.model.product.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);

    Optional<Company> findOptionalByName(String name);

}
