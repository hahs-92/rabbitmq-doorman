package com.sofka.reto.controller;

import com.sofka.reto.dto.RequestMessage;
import com.sofka.reto.service.RabbitMQService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/broker/departments/")
public class RabbitMQController {

    private String headerExchange = "header-exchange";
    private String fanoutExchange = "fanout-exchange";

    @Autowired
    RabbitMQService rabbitMQService;

    @PostMapping("/odd")
    public Mono<String> sendToOdd(
            @RequestBody RequestMessage request
    ) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("department", "odd");
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(request.getMessage(), messageProperties);

        return rabbitMQService.sendToOdd(this.headerExchange, "",message)
                .then(Mono.just("Message sent successfully"));
    }

    @PostMapping("/all")
    public Mono<String> sendToAll(
            @RequestBody RequestMessage request
    ) {
        return rabbitMQService.send(this.fanoutExchange, "",request.getMessage())
                .then(Mono.just("Message sent successfully"));
    }

}
