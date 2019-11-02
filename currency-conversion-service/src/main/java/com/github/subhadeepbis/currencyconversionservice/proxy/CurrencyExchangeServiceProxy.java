package com.github.subhadeepbis.currencyconversionservice.proxy;

import com.github.subhadeepbis.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @FeignClient is annotated to tell that this interface is a Feign Proxy
 * This annotation takes two parameters, name => which defines which microservice we need to invoke
 * url => where this microservice this deployed, since its deployed at 8002, we wrote localhost:8002
 */
@FeignClient(name="currency-exchange-service", url = "localhost:8002")
public interface CurrencyExchangeServiceProxy {
    /* This method was added so that Feign proxy knows which api to call*/
    @GetMapping("/currency-exchange-service/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
