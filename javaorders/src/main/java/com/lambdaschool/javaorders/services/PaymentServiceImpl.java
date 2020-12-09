package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Payment;
import com.lambdaschool.javaorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    @Override
    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }
}
