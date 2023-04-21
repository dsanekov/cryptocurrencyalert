package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.services.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DefaultController {
    private final CryptoCurrencyService cryptoCurrencyService;
    @Autowired
    public DefaultController(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @GetMapping("/start")
    private String startAlerting(){
        cryptoCurrencyService.startAlerting();
        return "Alerting has been started";
    }
}
