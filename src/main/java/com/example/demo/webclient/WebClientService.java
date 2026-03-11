package com.example.demo.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;

@Component
public class WebClientService {

    @Autowired
    WebClient webClient;

    public JsonNode findDataByEndPoint(String endpoint){
        String token= (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return webClient.get().uri(endpoint)
                .header("Authorization" ,token)
                .retrieve()
                .bodyToMono(JsonNode.class).block();
    }
}
