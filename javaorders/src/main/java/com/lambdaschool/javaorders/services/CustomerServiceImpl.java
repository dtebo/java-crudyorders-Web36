package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.models.Payment;
import com.lambdaschool.javaorders.repositories.CustomerRepository;
import com.lambdaschool.javaorders.repositories.OrderRepository;
import com.lambdaschool.javaorders.repositories.PaymentRepository;
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

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerByCustcode(long id) throws EntityNotFoundException {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Customer " + id + " Not Found!"));

        return c;
    }

    @Override
    public List<Customer> findByCustnameContainingIgnoringCase(String substring){
        List<Customer> customers = customerRepository.findByCustnameContainingIgnoringCase(substring);

        return customers;
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id){
        Customer updateCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));

        if(customer.getCustname() != null){
            updateCustomer.setCustname(customer.getCustname());
        }

        if(customer.getOutstandingamt() != 0.0){
            updateCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if(customer.getAgent() != null){
            updateCustomer.setAgent(customer.getAgent());
        }

        if(customer.getPaymentamt() != 0.0){
            updateCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if(customer.getOpeningamt() != 0.0){
            updateCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if(customer.getPhone() != null){
            updateCustomer.setPhone(customer.getPhone());
        }

        if(customer.getReceiveamt() != 0.0){
            updateCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if(customer.getGrade() != null){
            updateCustomer.setGrade(customer.getGrade());
        }

        if(customer.getCustcountry() != null){
            updateCustomer.setCustcountry(customer.getCustcountry());
        }

        if(customer.getWorkingarea() != null){
            updateCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if(customer.getCustcity() != null){
            updateCustomer.setCustcity(customer.getCustcity());
        }

        if(customer.getOrders().size() > 0){
            updateCustomer.getOrders().clear();
            for(Order o : customer.getOrders()){
                Order updateOrder = new Order();
                updateOrder.setOrdnum(o.getOrdnum());
                updateOrder.setCustomer(updateCustomer);

                updateOrder.setOrderdescription(o.getOrderdescription());
                updateOrder.setAdvanceamount(o.getAdvanceamount());

                for(Payment p : o.getPayments()){
                    Payment updatePayment = paymentRepository.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

                    updateOrder.getPayments().add(updatePayment);
                }

                updateCustomer.getOrders().add(updateOrder);
            }
        }

        return customerRepository.save(updateCustomer);
    }

    @Transactional
    @Override
    public Customer save(Customer customer){
        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0){
            customerRepository.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found!"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setAgent(customer.getAgent());

        if(customer.getOrders().size() > 0){
            newCustomer.getOrders().clear();

            for(Order o : customer.getOrders()){
                Order newOrder = new Order();
                newOrder.setOrdnum(o.getOrdnum());
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setCustomer(newCustomer);
                newOrder.setOrdamount(o.getOrdamount());

                for(Payment p : o.getPayments()){
                    Payment newPayment = paymentRepository.findById(p.getPaymentid())
                            .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

                    newOrder.getPayments().add(newPayment);
                }

                newCustomer.getOrders().add(newOrder);
            }
        }

        return customerRepository.save(newCustomer);
    }

    @Transactional
    @Override
    public void delete(long id){
        customerRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll(){
        customerRepository.deleteAll();
    }
}
