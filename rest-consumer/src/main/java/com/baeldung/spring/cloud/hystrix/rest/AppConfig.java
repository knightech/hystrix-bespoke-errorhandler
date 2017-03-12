package com.baeldung.spring.cloud.hystrix.rest;

import com.baeldung.spring.cloud.hystrix.rest.consumer.MyResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate forexOrderService() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(myResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public MyResponseErrorHandler myResponseErrorHandler() {
        return new MyResponseErrorHandler();
    }

}
