package com.jh.sinkerr;

import javax.persistence.Id;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SinkDto {
    @Id
    private Long id; // todo auto generated
    private String dest;
    @Setter
    private JsonNode payload;
    
    public SinkDto(final Long id, final String dest, final JsonNode payload) {
    	this.id = id;
    	this.dest = dest;
    	this.payload = payload;
    }
}
