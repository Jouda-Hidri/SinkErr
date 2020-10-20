package com.jh.sinkerr;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Consumer {
	
	@Autowired
	private SinkRepo repo;
		
	@Autowired
	private ObjectMapper mapper;

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "sink", groupId = "group_id")
	public void consumeErr(ConsumerRecord<?, ?> consumerRecord) throws IOException {
		String json = consumerRecord.value().toString();
		logger.info("received err data='{}'", json);
		Sink message = mapper.readValue(json, Sink.class);
		repo.save(message);
	}
	
	// --------------------------------------------------------------------------------------------------
	@KafkaListener(topics = "sink_ack", groupId = "group_id")
	public void consumeErr(String message, final Acknowledgment acknowledgment) throws IOException {
		logger.info(String.format("#### -> received error -> %s", message));
		// retry
		/*
		message.correct();
		this.producer.sendMessage(message);
		*/
		acknowledgment.acknowledge();
	}
}