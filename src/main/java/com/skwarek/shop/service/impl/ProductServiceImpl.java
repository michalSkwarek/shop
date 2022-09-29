package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.CategoryNotFoundException;
import com.skwarek.shop.exception.CompanyNotFoundException;
import com.skwarek.shop.exception.ProductNotFoundException;
import com.skwarek.shop.model.product.Category;
import com.skwarek.shop.model.product.Company;
import com.skwarek.shop.model.product.Product;
import com.skwarek.shop.repository.CategoryRepository;
import com.skwarek.shop.repository.CompanyRepository;
import com.skwarek.shop.repository.ProductRepository;
import com.skwarek.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Not found product with id: " + productId));
    }

    @Override
    public List<Product> findByCategory(String name) {
        Optional<Category> categoryDb = categoryRepository.findByName(name);

        if (categoryDb.isPresent()) {
            return productRepository.findByCategoryId(categoryDb.get().getId());
        } else {
            throw new CategoryNotFoundException("Not found category with name: " + name);
        }
    }

    @Override
    public List<Product> findByCompany(String name) {
        Optional<Company> companyDb = companyRepository.findByName(name);

        if (companyDb.isPresent()) {
            return productRepository.findByCompanyId(companyDb.get().getId());
        } else {
            throw new CompanyNotFoundException("Not found company with name: " + name);
        }
    }

    @Override
    public Product create(Product productRequest) {
        Product newProduct = new Product();
        newProduct.setName(productRequest.getName());
        newProduct.setDescription(productRequest.getDescription());
        newProduct.setUnitPrice(productRequest.getUnitPrice());
        newProduct.setCategory(null);
        newProduct.setCompany(null);
        newProduct.setPicture(null);
        newProduct.setProductSpecs(null);
        newProduct.setProductDetails(null);

        return productRepository.save(newProduct);
    }

    @Override
    public Product update(Long productId, Product productRequest) {
        Optional<Product> productDb = productRepository.findById(productId);

        if (productDb.isPresent()) {
            Product oldProduct = productDb.get();
            oldProduct.setName(productRequest.getName());
            oldProduct.setDescription(productRequest.getDescription());
            oldProduct.setUnitPrice(productRequest.getUnitPrice());

            return productRepository.save(oldProduct);
        } else {
            throw new ProductNotFoundException("Not found product with id: " + productId);
        }
    }

    @Override
    public void deleteById(Long productId) {
        boolean isProductExists = productRepository.existsById(productId);

        if (isProductExists) {
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException("Not found product with id: " + productId);
        }
    }

}
