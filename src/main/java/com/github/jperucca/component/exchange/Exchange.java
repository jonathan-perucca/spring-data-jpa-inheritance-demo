package com.github.jperucca.component.exchange;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity(name = "Exchange")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "exchange_type")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Exchange {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(STRING)
    private Status status;

    @Column(name = "exchange_type", insertable = false, updatable = false)
    @Enumerated(STRING)
    protected ExchangeType exchangeType;
}
