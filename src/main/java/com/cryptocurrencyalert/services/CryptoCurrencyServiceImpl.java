package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.dto.CurrencyDTO;
import com.cryptocurrencyalert.models.Image;
import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.ImagesRepository;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService{
    private final PeopleRepository peopleRepository;
    private final ImagesRepository imagesRepository;
    private final JavaMailSender javaMailSender;
    private final String uri = "https://api.binance.com/api/v3/ticker/price?symbol={symbol}";
    private volatile boolean stopAlerting = false;
    @Autowired
    public CryptoCurrencyServiceImpl(PeopleRepository peopleRepository, ImagesRepository imagesRepository, JavaMailSender javaMailSender) {
        this.peopleRepository = peopleRepository;
        this.imagesRepository = imagesRepository;
        this.javaMailSender = javaMailSender;
    }

    public boolean checkPriceByPerson(Person person) {
        boolean deletePerson = false;

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
                String text = person.getName() + ", " + person.getTicker() + " is " + person.getCondition() + " then " + person.getAlertPrice() + "! Price now - " + price;
                //sentMail(person.getEmail(),"Cryptocurrency Alert",text);
                sentMailWithImage(person.getEmail(),"Cryptocurrency Alert",text);
                deletePerson = true;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            log.info(e.getMessage());
        }
        return deletePerson;
    }

    public void checkAllPrices(){
        List<Person> optionalList = peopleRepository.findAll();
        CopyOnWriteArrayList<Person> deleteList = new CopyOnWriteArrayList();
        for(Person p : optionalList){
            if(checkPriceByPerson(p)){
                deleteList.add(p);
            }
        }
        peopleRepository.deleteAll(deleteList);
    }

    @Override
    public void startAlerting() {
        Thread thread = new Thread(() -> {
            while (!stopAlerting){
                try {
                    checkAllPrices();
                    Thread.sleep(60*1000);
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                    log.info(exception.getMessage());
                }
            }
        });
        thread.start();
        log.info("Alerting was started");
    }

    @Override
    public void stopAlerting() {
        stopAlerting = true;
        log.info("Alerting was stopped");
    }

    public void sentMail(String to, String subject, String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setText(text);
        mailMessage.setSubject(subject);

        try {
            javaMailSender.send(mailMessage);
            log.info("Message has been sent to " + to);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
            log.info(ex.getMessage());
        }
    }
    public void sentMailWithImage(String to, String subject, String text){

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setText("<html><body>"+text+"<br><br><img src='cid:image'></body></html>", true);

            Image image = getRandomImageFromDB();
            if(image == null){
                sentMail(to,subject,text);
                return;
            }
            final InputStreamSource imageBytes = new ByteArrayResource(image.getBytes());
            helper.addInline("image",imageBytes,image.getContentType());
        };

        try {
            javaMailSender.send(preparator);
            log.info("Message has been sent to " + to);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
            log.info(ex.getMessage());
        }
    }

    public Image getRandomImageFromDB(){
        List<Image> imageList = imagesRepository.findAll();
        if(!imageList.isEmpty()){
            int randomElementIndex
                    = ThreadLocalRandom.current().nextInt(imageList.size());
            return imageList.get(randomElementIndex);
        }
        return null;
    }
}
