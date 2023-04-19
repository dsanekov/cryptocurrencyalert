package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.models.Person;
import org.springframework.stereotype.Service;


public interface CryptoCurrencyService {
    float showPriceByPerson(Person person);
    public void showAllPrices();
}
