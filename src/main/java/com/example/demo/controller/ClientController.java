package com.example.demo.controller;

import com.example.demo.webclient.CustomHttpConnection;
import com.example.demo.webclient.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    CustomHttpConnection customHttpConnection;

    @Autowired
    WebClientService webClientService;

    @GetMapping("/url")
    public ResponseEntity<JsonNode> getDataFromUrl(@RequestBody Map<String,String> url) throws IOException {
        return  customHttpConnection.getDataFromUrl(url.get("url"));
    }

    @GetMapping("/web")
    public  ResponseEntity<JsonNode> getDataFromWebClient(@RequestBody Map<String,String>endpointmap){
        return ResponseEntity.ok(webClientService.findDataByEndPoint(endpointmap.get("endpoint")));
    }
}
