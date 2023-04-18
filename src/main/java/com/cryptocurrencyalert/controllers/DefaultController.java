package com.cryptocurrencyalert.controllers;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DefaultController {
    private final String uri = "https://api.binance.com/api/v3/ticker/price?symbol={symbol}";
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api")
    private String doApiCall(){
        Map<String, String> params = new HashMap<>();
        params.put("symbol","XRPUSDT");
        String response = "";
        try {
            response = restTemplate.getForObject(uri,String.class,params);
        }
        catch (HttpClientErrorException httpClientErrorException){
            return httpClientErrorException.toString();
        }
        return response;
    }
}
