package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.services.CryptoCurrencyService;
import com.cryptocurrencyalert.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Default", description = "Start and stop methods")
public class DefaultController {
    private final CryptoCurrencyService cryptoCurrencyService;
    private final ImageService imageService;
    @Autowired
    public DefaultController(CryptoCurrencyService cryptoCurrencyService, ImageService imageService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.imageService = imageService;
    }

    @GetMapping("/start")
    @Operation(summary = "Start alerting")
    private String startAlerting(){
        cryptoCurrencyService.startAlerting();
        return "Alerting has been started";
    }
    @GetMapping("/stop")
    @Operation(summary = "Stop alerting")
    private String stopAlerting(){
        cryptoCurrencyService.stopAlerting();
        return "Alerting has been canceled";
    }
}
