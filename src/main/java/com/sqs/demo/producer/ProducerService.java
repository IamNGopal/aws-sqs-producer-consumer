package com.sqs.demo.producer;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class ProducerService {

    private final SqsTemplate sqsTemplate;
    private final String queueName;


    public void sendMessage(String message) {
        log.info("Posting message to the queue: {}", message);
        this.sqsTemplate.send(queueName, MessageBuilder.withPayload(message).build());
    }
}
