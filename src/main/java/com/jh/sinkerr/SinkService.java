package com.jh.sinkerr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SinkService {

	@Autowired
	private SinkRepo repo;

	@Autowired
	private SinkProducer producer;
	
	@Autowired
	private ObjectMapper mapper;

	public List<Sink> show() {
		List<Sink> messages = new ArrayList<Sink>();
		repo.findAll().iterator().forEachRemaining(messages::add);
		return messages;
	}

	public Sink fix(SinkDto dto) throws JsonProcessingException {
		Optional<Sink> maybeExistingMessage = repo.findById(dto.getId());
		if (maybeExistingMessage.isPresent()) {
			final Sink ExistingMessage = maybeExistingMessage.get();
			String json = mapper.writeValueAsString(dto.getPayload());
			ExistingMessage.setPayload(json); // todo particial update
			repo.save(ExistingMessage);
			producer.retry(ExistingMessage); // todo partial update
			// todo delete existing message
			return ExistingMessage;
		}
		return null;
	}

}
