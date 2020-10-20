package com.jh.sinkerr;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Sink {
    @Id
    private Long id; // todo auto generated
    private String dest;
    @Setter
    private String payload;
    
    public Sink(final Long id, final String dest, final String payload) {
    	this.id = id;
    	this.dest = dest;
    	this.payload = payload;
    }
}
