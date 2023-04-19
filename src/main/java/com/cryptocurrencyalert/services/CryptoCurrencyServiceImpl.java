package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.dto.CurrencyDTO;
import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService{
    private final PeopleRepository peopleRepository;
    @Autowired
    public CryptoCurrencyServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public float showPriceByPerson(Person person) {
        String uri = "https://api.binance.com/api/v3/ticker/price?symbol={symbol}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        params.put("symbol",person.getTicker());
        float price = 0;

        try {
            String response = restTemplate.getForObject(uri,String.class,params);
            CurrencyDTO currencyDTO = mapper.readValue(response,CurrencyDTO.class);
            System.out.println("showPrice method " + currencyDTO);
            price = currencyDTO.getPrice();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return price;
    }
    @Override
    public void showAllPrices(){
        List<Person> optionalList = peopleRepository.findAll();
        for(Person p : optionalList){
            System.out.println(showPriceByPerson(p));
        }
    }
}
