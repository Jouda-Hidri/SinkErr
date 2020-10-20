package com.jh.sinkerr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SinkProducer {
	private static final Logger logger = LoggerFactory.getLogger(SinkProducer.class);

	@Autowired
	private KafkaTemplate<String, JsonNode> kafkaTemplate;
	
	@Autowired
	private ObjectMapper mapper;

	public void retry(final Sink message) throws JsonMappingException, JsonProcessingException {
		logger.info(String.format("#### -> retry -> %s", message.getPayload()));
		JsonNode payload = mapper.readTree(message.getPayload());
		kafkaTemplate.send(message.getDest(), payload);
	}

}
