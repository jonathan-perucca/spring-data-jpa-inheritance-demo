package com.github.jperucca.component.exchange;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ExchangeRepository<T extends Exchange> extends CrudRepository<T, Long> {

    Stream<Exchange> findByStatus(Status status);
}
