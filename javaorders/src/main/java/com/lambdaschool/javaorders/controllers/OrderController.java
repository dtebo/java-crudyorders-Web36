package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order newOrder){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/order/{id}", consumes = "application/json")
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody Order order){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/order")
    public ResponseEntity<?> deleteOrder(long id){
        orderService.findOrderByOrdnum(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
