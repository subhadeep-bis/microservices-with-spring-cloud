package com.subhadeep.microservices.limitsservice.controller;

import com.subhadeep.microservices.limitsservice.bean.LimitConfiguration;
import com.subhadeep.microservices.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsConfigurations() {
        // Below syntax is for hardcoding limit values
        //        return new LimitConfiguration(1000, 1);

        // Below Syntax is used for taking limit values from application.properties
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
//        return new LimitConfiguration(configuration.getLimits().get("maximum"), configuration.getLimits().get("minimum"));
    }
}