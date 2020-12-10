package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    // http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<?> findOrderByOrdernum(@PathVariable long id){
        Order order = orderService.findOrderByOrdnum(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order newOrder){
        newOrder.setOrdnum(0);

        newOrder = orderService.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI orderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();

        return new ResponseEntity<>(newOrder, responseHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/order/{id}", consumes = "application/json")
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody Order order){
        order.setOrdnum(id);
        Order o = orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable long id){
        orderService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
