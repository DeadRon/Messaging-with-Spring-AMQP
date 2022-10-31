package br.com.notification.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queueNotification(){
        return new Queue("orders.v1.order-created.send-notification"); //nome da fila. Vai avisar toda vez que uma venda ser criada
    }

    @Bean
    public Binding binding(){
        Queue queue = new Queue("orders.v1.order-created.send-notification");
        FanoutExchange exchange = new FanoutExchange("orders.v1.order-created");
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationEventApplicationListener(
            RabbitAdmin rabbitAdmin
    ){
        return event -> rabbitAdmin.initialize();
    }

    //Configurações necessários para fazer o recebimento de objetos complexos
    //Quando a fila trabalha com o recebimento/envio de objetos complexos
    //É preciso utilizar um bean de Jackson2JsonMessageConverter
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //Customiza o Bean do RabbitMQ para conseguir fazer o recebimento do objeto complexo
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter
    ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
