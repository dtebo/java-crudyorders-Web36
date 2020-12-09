package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    // http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/orders/order/{id}", produces = "application/json")
    public ResponseEntity<?> findOrderByOrdernum(@PathVariable long id){
        Order order = orderService.findOrderByOrdnum(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
