package com.example.demo.webclient;

import com.example.demo.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;

@Component
public class WebClientService {

    @Autowired
    WebClient webClient;

    public Mono<JsonNode> findDataByEndPoint(String endpoint){
        String token= (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return webClient.get().uri(endpoint)
                .header("Authorization" ,token)
                .retrieve()
                .bodyToMono(JsonNode.class).onErrorMap(error->new  CustomException(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
