package com.github.subhadeepbis.currencyconversionservice.controller;

import com.github.subhadeepbis.currencyconversionservice.model.CurrencyConversionBean;
import com.github.subhadeepbis.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CurrencyExchangeServiceProxy proxy;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable(value = "from") String from,
                                                  @PathVariable(value="to") String to,
                                                  @PathVariable(value="quantity") BigDecimal quantity) {
        // Problem 1 which Feign Solves
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        /**
         * "uriVariables" below is used to fetch the values that are to be replaced in the uri (http://localhost:8001...) eg, {from} will be searched
         * as a key in the uriVariables Map and since we have a key in the Map, it will fetch and assign it directly to the uri
         * http://localhost:8002/currency-exchange-service/from/{from}/to/{to}
         * if from = USD and to =  INR then the URI would look something like this
         * http://localhost:8002/currency-exchange-service/from/USD/to/INR
         * This method returns a ResponseEntity. If you would have used getForObject() then it would have returned an Object.
         */
        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(
                "http://localhost:8001/currency-exchange-service/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean body = responseEntity.getBody();
        body.setQuantity(quantity);
        body.setTotalCalculatedAmount(quantity.multiply(body.getConversionMultiple()));
        return body;
    }

    /**
     * Invoking a microservice using Feign Proxy. For all of this to work we have done the following things
     * 1. Added dependency spring.cloud.starter.openfeign
     * 2. @EnableFeignClients("base-package") annotation added in main class
     * 3. Created an interface which would work as Feign Proxy
     * 4. Annotated the interface with @FeignClient(name = "consumable-service", url = "url of consumable-service")
     * 5. Added a method in the interface which depicts the designated api to be called on the url.
     * 6. Autowire the interface in any of the class you want to use and directly call the method
     * @param from
     * @param to
     * @param quantity
     * @return
     */
    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyUsingFeign(@PathVariable(value = "from") String from,
                                                  @PathVariable(value="to") String to,
                                                  @PathVariable(value="quantity") BigDecimal quantity) {
        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
        return response;
    }
}
