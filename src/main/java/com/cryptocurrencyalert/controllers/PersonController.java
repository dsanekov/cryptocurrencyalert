package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class PersonController {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PersonController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
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

//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        Person person = peopleRepository.findById(id).orElse(null);
//        model.addAttribute("person", person);
//        return "edit";
//    }

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
}
