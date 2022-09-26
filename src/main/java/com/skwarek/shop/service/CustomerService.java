package com.skwarek.shop.service;

import com.skwarek.shop.model.user.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Long customerId);

    Customer create(Customer customerRequest);

    Customer update(Long customerId, Customer customerRequest);

    void deleteById(Long customerId);

}
