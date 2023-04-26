package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements PeopleService{
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public Person edit(int id, Person person) {
        Person person1 = peopleRepository.findById(id).orElse(null);
        if(person1 == null)
            return null;
        person1.setName(person.getName());
        person1.setAlertPrice(person.getAlertPrice());
        person1.setCondition(person.getCondition());
        person1.setEmail(person.getEmail());
        person1.setTicker(person.getTicker());
        peopleRepository.save(person1);
        return person1;
    }
}
