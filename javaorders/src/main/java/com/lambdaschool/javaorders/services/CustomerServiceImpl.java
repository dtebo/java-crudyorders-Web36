package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerByCustcode(long id) {
        Customer c = customerRepository.findCustomerByCustcode(id);

        if(c == null){
            throw new EntityNotFoundException("Customer " + id + " Not Found!");
        }

        return c;
    }

    @Override
    public List<Customer> findByCustnameContainingIgnoringCase(String substring){
        List<Customer> customers = customerRepository.findByCustnameContainingIgnoringCase(substring);

        return customers;
    }

    @Transactional
    @Override
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
}
