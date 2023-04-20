package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.dto.CurrencyDTO;
import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService{
    private final PeopleRepository peopleRepository;
    private final JavaMailSender javaMailSender;
    @Autowired
    public CryptoCurrencyServiceImpl(PeopleRepository peopleRepository, JavaMailSender javaMailSender) {
        this.peopleRepository = peopleRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public float checkPriceByPerson(Person person) {
        String uri = "https://api.binance.com/api/v3/ticker/price?symbol={symbol}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        params.put("symbol",person.getTicker());
        float price = 0;

        try {
            String response = restTemplate.getForObject(uri,String.class,params);
            CurrencyDTO currencyDTO = mapper.readValue(response,CurrencyDTO.class);
            price = currencyDTO.getPrice();

            if((person.getCondition().equals("Above") && price > person.getAlertPrice()) ||
                    (person.getCondition().equals("Below") && price < person.getAlertPrice())){
                String text = person.getName() + " " + person.getTicker() + " is " + person.getCondition() + " then " + person.getAlertPrice() + " price now - " + price;
                System.out.println(text);
                sentMail(person.getEmail(),"Cryptocurrency Alert",text);
                //TODO удалить это оповещение, если письмо отправлено.
                //TODO Хранить в бекенде картинку и отправлять ее в сообщении.
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return price;
    }
    @Override
    public void checkAllPrices(){
        List<Person> optionalList = peopleRepository.findAll();
        for(Person p : optionalList){
            checkPriceByPerson(p);
        }
    }

    @Override
    public void startAlerting() {
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    checkAllPrices();
                    Thread.sleep(60*1000);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        thread.start();
    }
    @Override
    public void sentMail(String to, String subject, String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("dsanekov@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setText(text);
        mailMessage.setSubject(subject);

        javaMailSender.send(mailMessage);

        System.out.println("mail sent");
    }
}
