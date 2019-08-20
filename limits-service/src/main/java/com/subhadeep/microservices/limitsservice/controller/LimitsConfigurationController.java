package com.subhadeep.microservices.limitsservice.controller;

import com.subhadeep.microservices.limitsservice.bean.LimitConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsConfigurations() {
        return new LimitConfiguration(1000, 1);
    }
}
