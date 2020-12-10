package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.models.Payment;
import com.lambdaschool.javaorders.repositories.CustomerRepository;
import com.lambdaschool.javaorders.repositories.OrderRepository;
import com.lambdaschool.javaorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    CustomerRepository customerRepository;

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
    public Order update(Order order, long id){
        Order updateOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found!"));

        if(order.getOrderdescription() != null){
            updateOrder.setOrderdescription(order.getOrderdescription());
        }

        if(order.getOrdamount() != 0.0){
            updateOrder.setOrdamount(order.getOrdamount());
        }

        if(order.getCustomer() != null){
            updateOrder.setCustomer(
                    customerRepository.findById(order.getCustomer().getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + order.getCustomer().getCustcode() + " Not Found!"))
            );
        }

        if(order.getPayments().size() > 0){
            updateOrder.getPayments().clear();

            for(Payment p : order.getPayments()){
                Payment updatePayment = paymentRepository.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

                updateOrder.getPayments().add(updatePayment);
            }
        }

        return orderRepository.save(updateOrder);
    }

    @Transactional
    @Override
    public Order save(Order order){
        Order newOrder = new Order();

        if(order.getOrdnum() != 0){
            orderRepository.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found!"));

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setCustomer(
                customerRepository.findById(order.getCustomer().getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer " + order.getCustomer().getCustcode() + " Not Found!"))
        );

        if(order.getPayments().size() > 0){
            newOrder.getPayments().clear();

            for(Payment p : order.getPayments()){
                Payment newPayment = paymentRepository.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

                newOrder.getPayments().add(newPayment);
            }
        }

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public void delete(long id){
        orderRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll(){
        orderRepository.deleteAll();
    }
}
