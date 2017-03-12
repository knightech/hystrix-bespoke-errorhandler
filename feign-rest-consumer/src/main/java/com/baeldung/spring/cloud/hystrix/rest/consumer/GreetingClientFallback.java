package com.baeldung.spring.cloud.hystrix.rest.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by pete on 05/03/2017.
 */
@Component
public class GreetingClientFallback implements GreetingClient {

    @Override
    public String greeting(@PathVariable("username") String username) {
        return "Hello Cock!";
    }
}
