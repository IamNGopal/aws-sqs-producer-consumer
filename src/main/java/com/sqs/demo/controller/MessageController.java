package com.sqs.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqs.demo.producer.ProducerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/v1.0/")
@RestController
public class MessageController {

    private final ProducerService producerService;

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody final String message){
    	producerService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
