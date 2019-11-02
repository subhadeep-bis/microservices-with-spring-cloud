package com.github.subhadeepbis.currencyconversionservice.controller;

import com.github.subhadeepbis.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConversionController {

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public CurrencyConversionController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable(value = "from") String from,
                                                  @PathVariable(value="to") String to,
                                                  @PathVariable(value="quantity") BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        /**
         * "uriVariables" below is used to fetch the values that are to be replaced in the uri (http://localhost:8002...) eg, {from} will be searched
         * as a key in the uriVariables Map and since we have a key in the Map, it will fetch and assign it directly to the uri
         * http://localhost:8002/currency-exchange-service/from/{from}/to/{to}
         * if from = USD and to =  INR then the URI would look something like this
         * http://localhost:8002/currency-exchange-service/from/USD/to/INR
         * This method returns a ResponseEntity. If you would have used getForObject() then it would have returned an Object.
         */
        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(
                "http://localhost:8002/currency-exchange-service/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean body = responseEntity.getBody();
        body.setQuantity(quantity);
        body.setTotalCalculatedAmount(quantity.multiply(body.getConversionMultiple()));
        return body;
    }
}
