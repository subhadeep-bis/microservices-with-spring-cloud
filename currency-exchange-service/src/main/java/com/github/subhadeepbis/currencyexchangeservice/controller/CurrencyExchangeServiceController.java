package com.github.subhadeepbis.currencyexchangeservice.controller;

import com.github.subhadeepbis.currencyexchangeservice.model.ExchangeValue;
import com.github.subhadeepbis.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeServiceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    Springboot provides something called environment from which we can get the port on which the application is running
    private Environment environment;
    private ExchangeValueRepository repository;

    @Autowired
    public CurrencyExchangeServiceController(Environment environment, ExchangeValueRepository repository) {
        this.repository = repository;
        this.environment = environment;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
        logger.info("Enter retrieveExchangeValue() -->");
        Integer port = Integer.parseInt(environment.getProperty("local.server.port"));
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        if(exchangeValue != null) {
            exchangeValue.setPort(port);
            logger.info("ExchangeValue --> {}", exchangeValue);
            logger.info("Exit retrieveExchangeValue() <--");
            return exchangeValue;
        }
        logger.info("Exit retrieveExchangeValue() <--");
        return new ExchangeValue();
    }

    @PostMapping("/add/currency/conversion/rate")
    public ResponseEntity<ExchangeValue> addCurrencyConversionRate(@Valid @RequestBody ExchangeValue exchangeValue) {
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        exchangeValue = repository.save(exchangeValue);
        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").
                buildAndExpand(exchangeValue.getId()).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(location);
        return new ResponseEntity<>(exchangeValue, httpHeaders, HttpStatus.CREATED);
    }
}
