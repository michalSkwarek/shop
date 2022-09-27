package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.CompanyNotFoundException;
import com.skwarek.shop.model.product.Company;
import com.skwarek.shop.repository.CompanyRepository;
import com.skwarek.shop.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Not found company with id: " + companyId));
    }

    @Override
    public Company create(Company companyRequest) {
        Company newCompany = new Company();
        newCompany.setName(companyRequest.getName());
        newCompany.setWebsite(companyRequest.getWebsite());
        newCompany.setPhoneNumber(companyRequest.getPhoneNumber());
        newCompany.setLogo(null);

        return companyRepository.save(newCompany);
    }

    @Override
    public Company update(Long companyId, Company companyRequest) {
        Optional<Company> companyDb = companyRepository.findById(companyId);

        if (companyDb.isPresent()) {
            Company oldCompany = companyDb.get();
            oldCompany.setName(companyRequest.getName());
            oldCompany.setWebsite(companyRequest.getWebsite());
            oldCompany.setPhoneNumber(companyRequest.getPhoneNumber());

            return companyRepository.save(oldCompany);
        } else {
            throw new CompanyNotFoundException("Not found company with id: " + companyId);
        }
    }

    @Override
    public void deleteById(Long companyId) {
        boolean isCompanyExists = companyRepository.existsById(companyId);

        if (isCompanyExists) {
            companyRepository.deleteById(companyId);
        } else {
            throw new CompanyNotFoundException("Not found company with id: " + companyId);
        }
    }

}
