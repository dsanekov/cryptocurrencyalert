package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import com.cryptocurrencyalert.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PersonController {
    private final PeopleRepository peopleRepository;
    private final PeopleService peopleService;
    @Autowired
    public PersonController(PeopleRepository peopleRepository, PeopleService peopleService) {
        this.peopleRepository = peopleRepository;
        this.peopleService = peopleService;
    }

    @GetMapping("/reg")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "newPerson";
    }

    @PostMapping("/reg")
    public String create(@ModelAttribute("person") Person person) {
        peopleRepository.save(person);
        return "redirect:/api/reg";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        model.addAttribute("person", person);
        return "edit";
    }
    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {

        peopleService.edit(id,person);
        return "redirect:/api/" + id;
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
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        if(person != null){
            peopleRepository.delete(person);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<Object> showAllPeople(){
        List<Person> people = peopleRepository.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
}
