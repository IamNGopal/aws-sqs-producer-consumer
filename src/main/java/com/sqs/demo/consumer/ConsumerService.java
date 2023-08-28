package com.sqs.demo.consumer;

import org.springframework.stereotype.Service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerService {

	
    @SqsListener(value= "test-queue-1", pollTimeoutSeconds="20")
    public void queueListener(String message) {
        log.info("Received message from the queue is: {}", message);
    }
}
