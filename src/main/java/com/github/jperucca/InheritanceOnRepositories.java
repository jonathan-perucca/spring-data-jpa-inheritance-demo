package com.github.jperucca;

import com.github.jperucca.component.exchange.ExchangeProductRepository;
import com.github.jperucca.component.exchange.ExchangeRepository;
import com.github.jperucca.component.exchange.ExchangeServiceRepository;
import com.github.jperucca.component.exchange.Status;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class InheritanceOnRepositories {

    private static final Logger logger = getLogger(InheritanceOnRepositories.class);

    @Autowired
    ExchangeRepository<?> exchangeRepository;

    @Autowired
    ExchangeProductRepository exchangeProductRepository;

    @Autowired
    ExchangeServiceRepository exchangeServiceRepository;

    @Autowired
    InitializingBean firstDemo;

    @Bean(name = "second")
    public InitializingBean startup() {
        return () -> {
            logger.info("[Inheritance on Repositories] started");

            exchangeRepository.findByStatus(Status.IN_PROGRESS)
                              .forEach(exchange -> logger.info("exchangeRepository.findByStatus(IN_PROGRESS) : {}", exchange));

            exchangeProductRepository.findByStatus(Status.READY)
                                     .forEach(exchangeProduct -> logger.info("exchangeProductRepository.findByStatus(READY) : {}", exchangeProduct));

            exchangeProductRepository.findByTitle("All in !")
                                     .forEach(exchangeProduct -> logger.info("exchangeProductRepository.findByTitle(\"All in !\") : {}", exchangeProduct));

            Map<String, Long> map = new HashMap<>();
            map.put("exchangeRepository.count()", exchangeRepository.count());
            map.put("exchangeProductRepository.count()", exchangeProductRepository.count());
            map.put("exchangeServiceRepository.count()", exchangeServiceRepository.count());
            map.entrySet().stream().forEach(entry -> logger.info("{} : {}", entry.getKey(), entry.getValue()));
        };
    }
}
