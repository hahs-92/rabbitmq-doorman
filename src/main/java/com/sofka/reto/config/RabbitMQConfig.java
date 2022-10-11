package com.sofka.reto.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private String apto01QueueName = "apto01Queue";
    private String apto02QueueName = "apto02Queue";
    private String apto03QueueName = "apto03Queue";
    private String apto04QueueName = "apto04Queue";

    private String fanoutExchange = " fanout-exchange";

    @Bean
    Queue apto01Queue() {return new Queue(apto01QueueName, false);}

    @Bean
    Queue apto02Queue() {return new Queue(apto02QueueName, false);}

    @Bean
    Queue apto03Queue() {return new Queue(apto03QueueName, false);}

    @Bean
    Queue apto04Queue() {return new Queue(apto04QueueName, false);}


    @Bean
    FanoutExchange exchange() { return  new FanoutExchange(fanoutExchange);}


    @Bean
    Binding apto01Binding(Queue apto01Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(apto01Queue).to(exchange);
    }

    @Bean
    Binding apto02Binding(Queue apto02Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(apto02Queue).to(exchange);
    }

    @Bean
    Binding apto03Binding(Queue apto03Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(apto03Queue).to(exchange);
    }

    @Bean
    Binding apto04Binding(Queue apto04Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(apto04Queue).to(exchange);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
