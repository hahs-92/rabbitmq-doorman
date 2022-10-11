package com.sofka.reto.service;

import com.sofka.reto.dto.RequestMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RabbitMQService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public Mono<Void> sendToAllApartaments(String exchange, String routingKey, String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return Mono.empty();
    }
}
