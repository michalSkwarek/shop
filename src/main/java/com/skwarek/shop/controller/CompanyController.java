package com.skwarek.shop.controller;

import com.skwarek.shop.model.product.Company;
import com.skwarek.shop.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAll();

        if (!companies.isEmpty()) {
            return ResponseEntity.ok(companies);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/companies/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("companyId") Long companyId) {
        Company company = companyService.findById(companyId);

        return ResponseEntity.ok(company);
    }

    @PostMapping(value = "/companies/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company companyRequest) {
        Company createdCompany = companyService.create(companyRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/companies")
                .path("/{companyId}").buildAndExpand(createdCompany.getId()).toUri();

        return ResponseEntity.created(location).body(createdCompany);
    }

    @PutMapping(value = "/companies/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable("companyId") Long companyId,
                                                   @RequestBody Company companyRequest) {
        Company updatedCompany = companyService.update(companyId, companyRequest);

        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping(value = "/companies/{companyId}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("companyId") Long companyId) {
        companyService.deleteById(companyId);

        return ResponseEntity.noContent().build();
    }

}
