package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findCustomerByCustcode(long id);
    List<Customer> findByCustnameContainingIgnoringCase(String substring);
    Customer save(Customer customer);
}
