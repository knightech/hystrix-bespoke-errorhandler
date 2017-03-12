package com.baeldung.spring.cloud.hystrix.rest.consumer;

import com.baeldung.spring.cloud.hystrix.rest.producer.GreetingController;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rest-producer", url = "http://localhost:9090", fallback = GreetingClientFallback.class)
public interface GreetingClient extends GreetingController {

}
