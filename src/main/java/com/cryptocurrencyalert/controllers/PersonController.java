package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.dto.PersonDTO;
import com.cryptocurrencyalert.models.Person;
import com.cryptocurrencyalert.repisitories.PeopleRepository;
import com.cryptocurrencyalert.services.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
@Tag(name = "Alerts", description = "Operations with alerts")
public class PersonController {
    private final PeopleService peopleService;
    @Autowired
    public PersonController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/reg")
    @Operation(summary = "Page for new alert")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "newPerson";
    }

    @PostMapping("/reg")
    @Operation(summary = "Save new alert")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "newPerson";
        }
        peopleService.save(person);
        return "redirect:/api";
    }

    @GetMapping("/{id}/edit")
    @Operation(summary = "Get alert for edit by id")
    public String edit(Model model, @PathVariable("id") int id) {
        Person person = peopleService.findById(id);
        model.addAttribute("person", person);
        return "edit";
    }
    @PatchMapping("/{id}/edit")
    @Operation(summary = "Edit alert by id")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "edit";
        }

        peopleService.edit(id,person);
        return "redirect:/api/" + id;
    }

    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "Get alert by id")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Person person = peopleService.findById(id);
        if(person == null){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person,HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete alert by id")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Person person = peopleService.findById(id);
        if(person != null){
            peopleService.delete(person);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @GetMapping()
    @Operation(summary = "Get all alerts")
    public ResponseEntity<Object> showAllPeople(){
        List<PersonDTO> personDTOS = peopleService.findAll();
        return new ResponseEntity<>(personDTOS, HttpStatus.OK);
    }
}
