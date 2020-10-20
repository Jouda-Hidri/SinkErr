package com.jh.sinkerr;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinkRepo extends CrudRepository<Sink, Long> {

}