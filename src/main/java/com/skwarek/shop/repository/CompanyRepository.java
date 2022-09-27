package com.skwarek.shop.repository;

import com.skwarek.shop.model.product.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
