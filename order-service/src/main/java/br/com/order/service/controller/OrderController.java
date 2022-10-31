package br.com.order.service.controller;

import br.com.order.service.domain.Order;
import br.com.order.service.rabbitmq.model.OrderCreatedEvent;
import br.com.order.service.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

    @Autowired
    private OrderRepository orders;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Order create(@RequestBody Order order){
        orders.save(order);
        String routingKey = "orders.v1.order-created";
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getValue());
        rabbitTemplate.convertAndSend(routingKey, "", event);
        return order;
    }

}
