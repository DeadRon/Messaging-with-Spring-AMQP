package br.com.notification.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//Listener para que o serviço de cashback consiga ler as mensagens criados no order service
@Component
public class OrderCreatedListener {

    @RabbitListener(queues = "orders.v1.order-created.send-notification")
    public void onOrderCreated(OrderCreatedEvent event){
        System.out.println("Id recebido: " + event.getId());
        System.out.println("valor: " + event.getValue());
    }

}
