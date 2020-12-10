package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.services.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<Customer> customers = customerService.findAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{id}", produces = "application/json")
    public ResponseEntity<?> findCustomerByCustcode(@PathVariable long id){
        Customer customer = customerService.findCustomerByCustcode(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/{substring}
    @GetMapping(value = "/namelike/{substring}", produces = "application/json")
    public ResponseEntity<?> findByCustnameContainingIgnoringCase(@PathVariable String substring){
        List<Customer> customers = customerService.findByCustnameContainingIgnoringCase(substring);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer newCustomer){
        newCustomer.setCustcode(0);

        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();

        responseHeaders.setLocation(newCustomerUri);

        return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/customer/{id}", consumes = "application/json")
    public ResponseEntity<?> updateCustomer(@PathVariable long id, @Valid @RequestBody Customer customer){
        customer.setCustcode(id);
        Customer c = customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/customer/{id}", consumes = "application/json")
    public ResponseEntity<?> patchCustomer(@PathVariable long id, @RequestBody Customer patchCustomer){
        customerService.update(patchCustomer, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long id){
        customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
