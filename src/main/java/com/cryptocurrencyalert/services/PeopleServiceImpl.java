package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.dto.PersonDTO;
import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Override
    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Person person) {
        peopleRepository.delete(person);
    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> personDTOS = new ArrayList<>();
        List<Person> people = peopleRepository.findAll();
        for(Person person : people){
            personDTOS.add(convertToPersonDTO(person));
        }
        return personDTOS;
    }
    private PersonDTO convertToPersonDTO(Person person){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(person,PersonDTO.class);
    }
}
