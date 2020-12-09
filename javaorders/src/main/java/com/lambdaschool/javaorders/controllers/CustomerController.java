package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/customers/orders", produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<Customer> customers = customerService.findAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customers/customer/{id}", produces = "application/json")
    public ResponseEntity<?> findCustomerByCustcode(@PathVariable long id){
        Customer customer = customerService.findCustomerByCustcode(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/{substring}
    @GetMapping(value = "/customers/namelike/{substring}", produces = "application/json")
    public ResponseEntity<?> findByCustnameContainingIgnoringCase(@PathVariable String substring){
        List<Customer> customers = customerService.findByCustnameContainingIgnoringCase(substring);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
