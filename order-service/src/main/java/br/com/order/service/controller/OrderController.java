package br.com.order.service.controller;

import br.com.order.service.domain.Order;
import br.com.order.service.rabbitmq.model.OrderCreatedEvent;
import br.com.order.service.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.order.service.controller.OrderDTO.fromDomain;
import static java.util.Optional.of;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public OrderDTO create(@RequestBody OrderDTO orderDTO){
        Order order = orderRepository.save(orderDTO.toDomain());
        String routingKey = "orders.v1.order-created";
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getValue());
        rabbitTemplate.convertAndSend(routingKey, "", event);
        return fromDomain(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderBayId(@PathVariable Long id){
        Order order = orderRepository.getReferenceById(id);
        return ResponseEntity.of(of(fromDomain(order)));
    }

}