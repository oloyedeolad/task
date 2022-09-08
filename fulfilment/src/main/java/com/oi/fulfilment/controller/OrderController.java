package com.oi.fulfilment.controller;


import com.oi.fulfilment.model.UtilityOrder;
import com.oi.fulfilment.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;


    @PostMapping
    public ResponseEntity<UtilityOrder> createOrder(@RequestBody UtilityOrder utilityOrder) {
       return ResponseEntity.ok().body( orderRepository.save(utilityOrder));
    }
}
