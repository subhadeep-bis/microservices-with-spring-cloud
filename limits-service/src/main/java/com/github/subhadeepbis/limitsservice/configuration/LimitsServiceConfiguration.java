package com.github.subhadeepbis.limitsservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "limits-service")
@Data
public class LimitsServiceConfiguration {

    // map because there are wo properties with second as prefix after "limits-service" hence used map to take  all in one
    private Map<String, Integer> second;
    private int minimum;
    private int maximum;
}
