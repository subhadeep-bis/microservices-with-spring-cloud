package com.github.subhadeepbis.currencyconversionservice.proxy;

import com.github.subhadeepbis.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @FeignClient is annotated to tell that this interface is a Feign Proxy
 * This annotation takes two parameters, name => which defines which microservice we need to invoke
 * url => where this microservice this deployed, since its deployed at 8002, we wrote localhost:8002
 * @Ribbon is also used as an annotation, It is used to enable load balancing and will automatically
 * distribute load among all the instances of a particular service. In our case its currency-exchange-service
 * imp: now the url part of @FeignClient is not needed because the url would be configured in a different way
 * we would not want to talk to one particular instance but we would want to distribute the load
 * between multiple instances of currency-exchange-service.
 * So, we will make changes in application.properties/bootstrap.properties
 */
//@FeignClient(name="currency-exchange-service", url = "localhost:8000")
@FeignClient(name="currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
    /* This method was added so that Feign proxy knows which api to call*/
    @GetMapping("/currency-exchange-service/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
