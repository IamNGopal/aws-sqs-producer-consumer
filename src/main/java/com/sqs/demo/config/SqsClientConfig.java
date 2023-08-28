package com.sqs.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SqsClientConfig {

	@Value("${cloud.aws.queue.name}")
	private String queueName;

	@Value("${cloud.aws.region}")
	private String region;

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;

	@Bean
	AwsCredentialsProvider awsCredentialsProvider() {
		AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
		return StaticCredentialsProvider.create(awsCredentials);
	}

	@Bean
	SqsAsyncClient sqsAsyncClient() {
		return SqsAsyncClient.builder().region(Region.of(region)).credentialsProvider(awsCredentialsProvider()).build();
	}

	@Bean
	SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory() {
		return SqsMessageListenerContainerFactory.builder()
				.configure(options -> options.acknowledgementMode(AcknowledgementMode.ON_SUCCESS))
				.sqsAsyncClient(sqsAsyncClient()).build();
	}

	@Bean("queueName")
	String sqsQueueName() {
		return queueName;
	}
}
