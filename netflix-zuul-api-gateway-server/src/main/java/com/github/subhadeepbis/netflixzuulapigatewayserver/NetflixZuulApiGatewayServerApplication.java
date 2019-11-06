package com.github.subhadeepbis.netflixzuulapigatewayserver;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixZuulApiGatewayServerApplication.class, args);
    }

    /**
     * This Bean is configured for Spring cloud sleuth.
     * This we need to trace all the requests, we use ALWAYS_SAMPLER bean
     * @return
     */
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
