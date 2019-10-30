package com.subhadeepbis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="bank-account-service")
public class Configuration {

	@Getter
	@Setter
	private Double minBalance;
	
	@Getter
	@Setter
	private Double maxBalance;
}
