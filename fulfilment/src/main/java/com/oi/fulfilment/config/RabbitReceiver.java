package com.oi.fulfilment.config;


import com.oi.fulfilment.model.UtilityOrder;
import com.oi.fulfilment.repositories.OrderRepository;
import com.oi.shared.dtos.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "rabbitmq.queue", id = "listener")
public class RabbitReceiver {

    @Autowired
    OrderRepository orderRepository;


    @RabbitHandler
    public void receiver(OrderDto orderDto) {
        ModelMapper modelMapper = new ModelMapper();
        UtilityOrder utilityOrder = modelMapper.map(orderDto, UtilityOrder.class);

        orderRepository.save(utilityOrder);
    }
}
