package com.github.subhadeepbis.currencyconversionservice.controller;

import com.github.subhadeepbis.currencyconversionservice.model.CurrencyConversionBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConversionController {

    private Environment environment;

    @Autowired
    public CurrencyConversionController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable(value = "from") String from,
                                                  @PathVariable(value="to") String to,
                                                  @PathVariable(value="quantity") String quantity) {
        String port = environment.getProperty("local.server.port");
        return new CurrencyConversionBean(1000L, from, to, BigDecimal.valueOf(75), BigDecimal.valueOf(Integer.parseInt(quantity)), BigDecimal.valueOf(75000), Integer.parseInt(port));
    }
}
