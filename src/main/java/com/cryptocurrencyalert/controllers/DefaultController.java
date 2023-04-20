package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.services.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DefaultController {
    private final String uri = "https://api.binance.com/api/v3/ticker/price?symbol={symbol}";
    private RestTemplate restTemplate = new RestTemplate();
    private final CryptoCurrencyService cryptoCurrencyService;
    @Autowired
    public DefaultController(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

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
    @GetMapping("/show")
    private String showAllPrices(){
        cryptoCurrencyService.checkAllPrices();
        return "ok";
    }
    @GetMapping("/start")
    private String startAlerting(){
        cryptoCurrencyService.startAlerting();
        return "ok";
    }
//    @GetMapping("/sent")
//    private String sentMail(){
//        cryptoCurrencyService.sentMail("Hello");
//        return "mail sent";
//    }
}
