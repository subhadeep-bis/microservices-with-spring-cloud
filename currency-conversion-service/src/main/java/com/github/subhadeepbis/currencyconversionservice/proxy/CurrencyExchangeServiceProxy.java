package com.github.subhadeepbis.currencyconversionservice.proxy;

import com.github.subhadeepbis.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @FeignClient is annotated to tell that this interface is a Feign Proxy
 * This annotation takes two parameters, name => which defines which microservice we need to invoke
 * url => where this microservice this deployed, since its deployed at 8001, we wrote localhost:8001
 * --------------------------------------------------------------------------------------------------------------
 * @Ribbon is also used as an annotation, It is used to enable load balancing and will automatically
 * distribute load among all the instances of a particular service. In our case its currency-exchange-service
 * imp: now the url part of @FeignClient is not needed because the url would be configured in a different way
 * we would not want to talk to one particular instance but we would want to distribute the load
 * between multiple instances of currency-exchange-service.
 * So, we will make changes in application.properties/bootstrap.properties
 * --------------------------------------------------------------------------------------------------------------
 * Till now we were calling the Zuul api gateway to invoke any microservice, however we also want the inner microservice
 * calls to also get routed through Zuul Api gateway.
 * Since, this service is calling the currency-exchange-service, so we need to make changes over here so that the calls
 * goes through the zuul gateway.
 * Mentioning the name of "currency-exchange-service" directly allowed feign and ribbon to understand which microservice to call
 * since every detail about "currency-exchange-service" is caught by them using service-discovery from the EurekaNamingServer.
 * now, just by changing "currency-exchange-service" with "netflix-zuul-api-gateway-server" will result in the calls to go through
 * zuul api gateway, however, the uri (above retrieveExchangeValue() method) mentioned below for the feign proxy won't work.
 * why? because now the request goes through "netflix-zuul-api-gateway-server" i.e. zuul and calling via zuul follows the following notation
 * localhost:{zuul:port}/{application-name}/{uri} which means the call should be
 * http:localhost:8765/currency-exchange-service/currency-exchange/from/{from}/to/{to}
 * however, following the previous code would create the following uri and send the request over there
 * http:localhost:8765/currency-exchange/from/{from}/to/{to}
 * and will ultimately result in a "white label error"
 * so, we will also make the changes in the uri by appending the application name in the front of the uri. The changes can be
 * seen below.
 */
//@FeignClient(name="currency-exchange-service", url = "localhost:8001")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
    /* This method was added so that Feign proxy knows which api to call*/
//    @GetMapping("/currency-exchange/from/{from}/to/{to}") //previous code
    @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
