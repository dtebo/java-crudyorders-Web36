package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order findOrderByOrdnum(long id){
        Order order = orderRepository.findOrderByOrdnum(id);

        if(order == null){
            throw new EntityNotFoundException("Order " + id + " Not Found!");
        }

        return order;
    }

    @Transactional
    @Override
    public Order save(Order order){
        return orderRepository.save(order);
    }
}
