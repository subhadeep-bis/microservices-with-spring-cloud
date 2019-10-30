package com.github.subhadeepbis.limitsservice.controller;

import com.github.subhadeepbis.limitsservice.configuration.LimitsServiceConfiguration;
import com.github.subhadeepbis.limitsservice.model.LimitConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsServiceController {

    private LimitsServiceConfiguration configuration;

    public LimitsServiceController(LimitsServiceConfiguration limitsServiceConfiguration) {
        this.configuration = limitsServiceConfiguration;
    }

    @GetMapping("/staticLimits")
    public LimitConfiguration retrieveStaticLimitsConfigurations() {
        return new LimitConfiguration(1000,1, 999, 2);
    }

    /**
     * Uncomment the properties from application.properties so that it can be used by the following api
     * Normally if you dont uncomment it will still work, because the data will fetched from the git.
     * @return
     */
    @GetMapping("/limitsFromPropertiesFile")
    public LimitConfiguration retrieveLimitsFromProperties() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum(),
                configuration.getSecond().get("maximum"), configuration.getSecond().get("minimum"));
    }

    @GetMapping("/limitsFromConfiguration")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum(),
                configuration.getSecond().get("maximum"), configuration.getSecond().get("minimum"));
    }

}
