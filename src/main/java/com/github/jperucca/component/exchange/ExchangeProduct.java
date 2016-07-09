package com.github.jperucca.component.exchange;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity @NoArgsConstructor @Getter
public class ExchangeProduct extends Exchange {

    private String title;

    @Builder
    public ExchangeProduct(Long id, Status status, ExchangeType exchangeType, String title) {
        super(id, status, exchangeType);
        this.title = title;
    }
}
