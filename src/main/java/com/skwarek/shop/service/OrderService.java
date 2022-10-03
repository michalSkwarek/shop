package com.skwarek.shop.service;

import com.skwarek.shop.model.order.Order;

import java.util.List;

public interface OrderService {

    List<Order> findByCustomerId(Long customerId);

    Order findById(Long orderId);

    Order create(Long customerId, Order orderRequest);

    Order update(Long orderId, Order orderRequest);

    void deleteById(Long orderId);

}
