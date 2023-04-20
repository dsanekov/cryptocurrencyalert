package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.models.Person;


public interface CryptoCurrencyService {
    float checkPriceByPerson(Person person);
    void checkAllPrices();
    void startAlerting();
    void sentMail(String to, String subject, String text);
}
