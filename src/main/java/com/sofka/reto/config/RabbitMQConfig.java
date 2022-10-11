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

    private String apto05QueueName = "apto05Queue";

    private String apto06QueueName = "apto06Queue";

    private String headerExchange = "header-exchange";
    private String fanoutExchange = "fanout-exchange";

    @Bean
    Queue apto01Queue() {return new Queue(apto01QueueName, false);}

    @Bean
    Queue apto02Queue() {return new Queue(apto02QueueName, false);}

    @Bean
    Queue apto03Queue() {return new Queue(apto03QueueName, false);}

    @Bean
    Queue apto04Queue() {return new Queue(apto04QueueName, false);}

    @Bean
    Queue apto05Queue() {return new Queue(apto05QueueName, false);}

    @Bean
    Queue apto06Queue() {return new Queue(apto06QueueName, false);}



    @Bean
    FanoutExchange fanoutExchange() { return new FanoutExchange(fanoutExchange);}
    @Bean
    HeadersExchange headerExchange() {
        return new HeadersExchange(headerExchange);
    }


    @Bean
    Binding apto01BindingFanout() {
        return BindingBuilder.bind(this.apto01Queue()).to(this.fanoutExchange());
    }

    @Bean
    Binding apto02BindingFanout() {
        return BindingBuilder.bind(this.apto02Queue()).to(this.fanoutExchange());
    }

    @Bean
    Binding apto03BindingFanout() {
        return BindingBuilder.bind(this.apto03Queue()).to(this.fanoutExchange());
    }

    @Bean
    Binding apto04BindingFanout() {
        return BindingBuilder.bind(this.apto04Queue()).to(this.fanoutExchange());
    }

    @Bean
    Binding apto05BindingFanout() {
        return BindingBuilder.bind(this.apto05Queue()).to(this.fanoutExchange());
    }

    @Bean
    Binding apto06BindingFanout() {
        return BindingBuilder.bind(this.apto06Queue()).to(this.fanoutExchange());
    }

    @Bean
    Binding apto01BindingHeader() {
        return BindingBuilder.bind(this.apto01Queue()).to(this.headerExchange()).where("department").matches("odd");
    }

    @Bean
    Binding apto02BindingHeader() {
        return BindingBuilder.bind(this.apto02Queue()).to(this.headerExchange()).where("department").matches("pair");
    }

    @Bean
    Binding apto03BindingHeader() {
        return BindingBuilder.bind(this.apto03Queue()).to(this.headerExchange()).where("department").matches("odd");
    }

    @Bean
    Binding apto04BindingHeader() {
        return BindingBuilder.bind(this.apto04Queue()).to(this.headerExchange()).where("department").matches("pair");
    }

    @Bean
    Binding apto05BindingHeader() {
        return BindingBuilder.bind(this.apto05Queue()).to(this.headerExchange()).where("department").matches("odd");
    }

    @Bean
    Binding apto06BindingHeader() {
        return BindingBuilder.bind(this.apto06Queue()).to(this.headerExchange()).where("department").matches("pair");
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
