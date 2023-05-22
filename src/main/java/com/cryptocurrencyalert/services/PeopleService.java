package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.dto.PersonDTO;
import com.cryptocurrencyalert.models.Person;

import java.util.List;

public interface PeopleService {
    Person edit (int id, Person person);
    void save (Person person);
    Person findById(int id);
    void delete(Person person);
    List<PersonDTO> findAll();
}
