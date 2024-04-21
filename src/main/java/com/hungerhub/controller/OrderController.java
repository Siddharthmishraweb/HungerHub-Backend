package com.hungerhub.controller;

import com.hungerhub.model.Order;
import com.hungerhub.model.User;
import com.hungerhub.request.OrderRequest;
import com.hungerhub.response.PaymentResponse;
import com.hungerhub.service.OrderService;
import com.hungerhub.service.PaymentService;
import com.hungerhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req, user);


        System.out.println("+++++++++++++++++++++========================================================================================================================================="+order.getTotalPrice());
        PaymentResponse res = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}