package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.CompanyNotFoundException;
import com.skwarek.shop.exception.CustomerNotFoundException;
import com.skwarek.shop.exception.OrderNotFoundException;
import com.skwarek.shop.model.order.Order;
import com.skwarek.shop.model.user.Customer;
import com.skwarek.shop.repository.CustomerRepository;
import com.skwarek.shop.repository.OrderRepository;
import com.skwarek.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findByCustomerId(Long customerId) {
        boolean isCustomerExists = customerRepository.existsById(customerId);

        if (isCustomerExists) {
            return orderRepository.findByCustomerId(customerId);
        } else {
            throw new CustomerNotFoundException("Not found customer with id: " + customerId);
        }
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Not found order with id: " + orderId));
    }

    @Override
    public Order create(Long customerId, Order orderRequest) {
        Optional<Customer> customerDb = customerRepository.findById(customerId);

        if (customerDb.isPresent()) {
            Order newOrder = new Order();
            newOrder.setCreatedAt(LocalDateTime.now());
            newOrder.setAmount(orderRequest.getAmount());
            newOrder.setCustomer(customerDb.get());

            return orderRepository.save(newOrder);
        } else {
            throw new CustomerNotFoundException("Not found customer with id: " + customerId);
        }
    }

    @Override
    public Order update(Long orderId, Order orderRequest) {
        Optional<Order> orderDb = orderRepository.findById(orderId);

        if (orderDb.isPresent()) {
            Order oldOrder = orderDb.get();
            oldOrder.setAmount(orderRequest.getAmount());

            return orderRepository.save(oldOrder);
        } else {
            throw new CompanyNotFoundException("Not found order with id: " + orderId);
        }
    }

    @Override
    public void deleteById(Long orderId) {
        boolean isCompanyExists = orderRepository.existsById(orderId);

        if (isCompanyExists) {
            orderRepository.deleteById(orderId);
        } else {
            throw new CompanyNotFoundException("Not found order with id: " + orderId);
        }
    }

}
