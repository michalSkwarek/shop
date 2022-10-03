package com.skwarek.shop.controller;

import com.skwarek.shop.model.order.Order;
import com.skwarek.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<List<Order>> getAllOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
        List<Order> orders = orderService.findByCustomerId(customerId);

        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findById(orderId);

        return ResponseEntity.ok(order);
    }

    @PostMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable("customerId") Long customerId,
                                             @RequestBody Order orderRequest) {
        Order createdOrder = orderService.create(customerId, orderRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/orders")
                .path("/{orderId}").buildAndExpand(createdOrder.getId()).toUri();

        return ResponseEntity.created(location).body(createdOrder);
    }

    @PutMapping(value = "/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Long orderId,
                                             @RequestBody Order orderRequest) {
        Order updatedOrder = orderService.update(orderId, orderRequest);

        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping(value = "/orders/{orderId}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable("orderId") Long orderId) {
        orderService.deleteById(orderId);

        return ResponseEntity.noContent().build();
    }

}
