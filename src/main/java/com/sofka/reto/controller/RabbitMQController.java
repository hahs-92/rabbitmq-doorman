package com.sofka.reto.controller;

import com.sofka.reto.dto.RequestMessage;
import com.sofka.reto.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/broker/")
public class RabbitMQController {

    @Autowired
    RabbitMQService rabbitMQService;

    @PostMapping("/fanout")
    public Mono<String> sendToAllApartaments(@RequestBody RequestMessage request, @RequestParam("exchangeName") String exchange) {
        System.out.println(request);
        return rabbitMQService.sendToAllApartaments(exchange, "",request.getMessage())
                .then(Mono.just("Message sent successfully"));
    }

}
