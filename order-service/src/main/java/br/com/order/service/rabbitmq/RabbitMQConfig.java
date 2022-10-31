package br.com.order.service.rabbitmq;

import org.springframework.amqp.core.FanoutExchange;
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
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("orders.v1.order-created");
    }


    //Para que a fila inicialize é preciso configurar dois bens o primeiro
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    //define um listener para escutar a fila assim que a aplicação ser inicializada
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationEventApplicationListener(
            RabbitAdmin rabbitAdmin
    ){
        return event -> rabbitAdmin.initialize();
    }

    //Configurações necessários para fazer o envio de objetos complexos
    //Quando a fila trabalha com o recebimento/envio de objetos complexos
    //É preciso utilizar um bean de Jackson2JsonMessageConverter
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //Customiza o Bean do RabbitMQ para conseguir fazer o envio do objeto complexo
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