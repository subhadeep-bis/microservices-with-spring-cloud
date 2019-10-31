package com.github.subhadeepbis.currencyexchangeservice.controller;

import com.github.subhadeepbis.currencyexchangeservice.model.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange-service")
public class CurrencyExchangeServiceController {

//    Springboot provides something called environment from which we can get the port on which the application is running
    @Autowired
    private Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
        Integer port = Integer.parseInt(environment.getProperty("local.server.port"));
        return new ExchangeValue(1001L, from, to, BigDecimal.valueOf(1000), port);
    }
}
