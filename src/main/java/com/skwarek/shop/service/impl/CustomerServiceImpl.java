package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.CustomerNotFoundException;
import com.skwarek.shop.model.user.Customer;
import com.skwarek.shop.repository.CustomerRepository;
import com.skwarek.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Not found customer with id: " + customerId));
    }

    @Override
    public Customer create(Customer customerRequest) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerRequest.getFirstName());
        newCustomer.setLastName(customerRequest.getLastName());
        newCustomer.setBirthDate(customerRequest.getBirthDate());
        newCustomer.setPhoneNumber(customerRequest.getPhoneNumber());
        newCustomer.setAccount(null);
        newCustomer.setBillingAddress(null);

        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer update(Long customerId, Customer customerRequest) {
        Optional<Customer> customerDb = customerRepository.findById(customerId);

        if (customerDb.isPresent()) {
            Customer oldCustomer = customerDb.get();
            oldCustomer.setFirstName(customerRequest.getFirstName());
            oldCustomer.setLastName(customerRequest.getLastName());
            oldCustomer.setBirthDate(customerRequest.getBirthDate());
            oldCustomer.setPhoneNumber(customerRequest.getPhoneNumber());

            return customerRepository.save(oldCustomer);
        } else {
            throw new CustomerNotFoundException("Not found customer with id: " + customerId);
        }
    }

    @Override
    public void deleteById(Long customerId) {
        boolean isCustomerExists = customerRepository.existsById(customerId);

        if (isCustomerExists) {
            customerRepository.deleteById(customerId);
        } else {
            throw new CustomerNotFoundException("Not found customer with id: " + customerId);
        }
    }

}
