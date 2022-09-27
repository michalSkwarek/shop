package com.skwarek.shop.service;

import com.skwarek.shop.model.product.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findById(Long companyId);

    Company create(Company companyRequest);

    Company update(Long companyId, Company companyRequest);

    void deleteById(Long companyId);

}
