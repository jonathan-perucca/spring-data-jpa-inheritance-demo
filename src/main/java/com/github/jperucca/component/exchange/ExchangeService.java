package com.github.jperucca.component.exchange;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity @NoArgsConstructor
public class ExchangeService extends Exchange {

    private Long likes;

    @Builder
    public ExchangeService(Long id, Status status, ExchangeType exchangeType, Long likes) {
        super(id, status, exchangeType);
        this.likes = likes;
    }
}
