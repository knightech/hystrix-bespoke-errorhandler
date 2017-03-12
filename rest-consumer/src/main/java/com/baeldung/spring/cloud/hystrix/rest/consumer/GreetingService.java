package com.baeldung.spring.cloud.hystrix.rest.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GreetingService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting(String username) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:9090/greeting/{username}", HttpMethod.GET, request, String.class, username);

        String responseBody = response.getBody();

        try {

            if (RestUtil.isError(response.getStatusCode())) {
                MyErrorResource error = objectMapper.readValue(responseBody, MyErrorResource.class);
                MyErrorResource.ErrorItem[] item = error.getErrorItems();

                System.out.format("Status: %s and Message %s, %s, %s", error.getHttpStatus(), item[0].getErrorCode(), item[0].getMessage(), item[0].getDeveloperMessage());

                throw new RestClientException("[" + error.getHttpStatus() + "]");
            } else {
                User theResult = objectMapper.readValue(responseBody, GreetingService.User.class);
                return theResult.getName();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String name) {
            this.name = name;
        }

        public User() {

        }
    }

    private String defaultGreeting(String username) {
        return "Hello User!";
    }
}
