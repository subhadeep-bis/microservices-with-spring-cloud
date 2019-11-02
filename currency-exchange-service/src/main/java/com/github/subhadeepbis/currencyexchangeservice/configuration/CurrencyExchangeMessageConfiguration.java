package com.github.subhadeepbis.currencyexchangeservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("currency-exchange-service-message")
@PropertySource("classpath:message.properties")
@Data
public class CurrencyExchangeMessageConfiguration {
    private String validationFailed;
}
