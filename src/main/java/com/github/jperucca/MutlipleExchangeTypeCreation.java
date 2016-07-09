package com.github.jperucca;

import com.github.jperucca.component.exchange.Exchange;
import com.github.jperucca.component.exchange.ExchangeProduct;
import com.github.jperucca.component.exchange.ExchangeRepository;
import com.github.jperucca.component.exchange.ExchangeService;
import com.github.jperucca.component.exchange.Status;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class MutlipleExchangeTypeCreation {

    private static final Logger logger = getLogger(MutlipleExchangeTypeCreation.class);

    @Autowired
    ExchangeRepository<Exchange> exchangeRepository;

    @Bean(name = "first")
    public InitializingBean startup() {
        return () -> {
            logger.info("[multiple exchange type creation] started");
            Stream  .of(buildExchange(), buildExchangeProduct(), buildExchangeService())
                    .map(exchangeRepository::save)
                    .forEach(exchange -> logger.info("inserted exchange {}", exchange));

            exchangeRepository
                    .findAll()
                    .forEach(exchange -> logger.info("exchangeRepository.findAll() found : {}", exchange));
        };
    }

    private Exchange buildExchange() {
        Exchange exchange = new Exchange();
        exchange.setStatus(Status.IN_PROGRESS);
        return exchange;
    }

    private ExchangeProduct buildExchangeProduct() {
        return ExchangeProduct.builder().status(Status.READY).title("All in !").build();
    }

    private ExchangeService buildExchangeService() {
        return ExchangeService.builder().status(Status.FINISHED).likes(300L).build();
    }
}
