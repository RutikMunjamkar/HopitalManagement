package com.example.demo.webclient;

import com.example.demo.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class CustomHttpConnection {

    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity<JsonNode> getDataFromUrl(String endpoint) throws IOException {
        URL url=new URL(endpoint);
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        String token= getContext().getAuthentication().getCredentials().toString();
        connection.setRequestProperty("Authorization", token);
        int responseCode=connection.getResponseCode();
        if(responseCode!=200){
            throw new CustomException(connection.getRequestMethod(), HttpStatus.valueOf(responseCode));
        }

        BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder builder=new StringBuilder();
        while((line=reader.readLine())!=null){
            builder.append(line);
        }
        return ResponseEntity.ok(objectMapper.readTree(builder.toString()));
    }
}
