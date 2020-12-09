package com.lambdaschool.javaorders.repositories;

import com.lambdaschool.javaorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
    Customer findCustomerByCustcode(long id);
    List<Customer> findByCustnameContainingIgnoringCase(String substring);
}
