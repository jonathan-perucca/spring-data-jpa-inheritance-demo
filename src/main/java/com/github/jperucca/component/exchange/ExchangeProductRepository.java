package com.github.jperucca.component.exchange;

import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ExchangeProductRepository extends ExchangeRepository<ExchangeProduct> {

    Stream<ExchangeProduct> findByTitle(String title);
}
