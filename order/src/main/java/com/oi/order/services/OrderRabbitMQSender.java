package com.oi.order.services;


import com.oi.shared.dtos.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderRabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private Queue queue;

    public void send(OrderDto orderDto) {
        rabbitTemplate.convertAndSend(queue.getName(), orderDto);
        log.info("Sending Message to the Queue : " + orderDto.toString());
    }
}
