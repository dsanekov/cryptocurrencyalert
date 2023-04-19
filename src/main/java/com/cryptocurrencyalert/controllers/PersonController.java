package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.dto.PersonDTO;
import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PersonController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/reg")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "new";
    }

    @PostMapping("/reg")
    public String create(@ModelAttribute("person") Person person) {
        peopleRepository.save(person);
        return "redirect:/people/reg";
    }
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        if(person == null){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person,HttpStatus.OK);
    }
}
