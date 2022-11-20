package com.skwarek.shop.controller;

import com.skwarek.shop.model.user.Customer;
import com.skwarek.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();

        if (!customers.isEmpty()) {
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        Customer customer = customerService.findById(customerId);

        return ResponseEntity.ok(customer);
    }

    @PostMapping(value = "/customers/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerRequest) {
        Customer createdCustomer = customerService.create(customerRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/customers")
                .path("/{customerId}").buildAndExpand(createdCustomer.getId()).toUri();

        return ResponseEntity.created(location).body(createdCustomer);
    }

    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Long customerId,
                                                   @RequestBody Customer customerRequest) {
        Customer updatedCustomer = customerService.update(customerId, customerRequest);

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable("customerId") Long customerId) {
        customerService.deleteById(customerId);

        return ResponseEntity.noContent().build();
    }

}
