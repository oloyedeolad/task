package com.oi.order.controllers;



import com.oi.order.services.OrderRabbitMQSender;
import com.oi.shared.dtos.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderRabbitMQSender rabbitMQSender;

    @PostMapping("")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        rabbitMQSender.send(orderDto);
        return ResponseEntity.ok().build();
    }
}
