package com.hungerhub.service;

import com.hungerhub.model.Order;
import com.hungerhub.response.PaymentResponse;

public interface PaymentService{
    public PaymentResponse createPaymentLink(Order order);

}
