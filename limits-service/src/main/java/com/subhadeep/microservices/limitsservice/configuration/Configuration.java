package com.subhadeep.microservices.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "limits-service")
public class Configuration {
    private int minimum;
    private int maximum;

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

//    # Below is for directly taking the key value pairs as a Map. This will help us in decreasing the usage of variables
//    private Map<String, Integer> limits;
//
//    public Map<String, Integer> getLimits() {
//        return limits;
//    }
//
//    public void setLimits(Map<String, Integer> limits) {
//        this.limits = limits;
//    }
}
