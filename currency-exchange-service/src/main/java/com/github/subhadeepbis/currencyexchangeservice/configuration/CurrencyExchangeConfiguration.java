package com.github.subhadeepbis.currencyexchangeservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("currency-exchange-service")
@Data
public class CurrencyExchangeConfiguration {

}
