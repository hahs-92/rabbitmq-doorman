package com.sofka.reto.service;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = { "apto01Queue", "apto02Queue", "apto03Queue","apto04Queue","apto05Queue","apto06Queue"} )
    public void receivedAllMessages(String message) {
        log.info("Received message: " + message);
    }
}
