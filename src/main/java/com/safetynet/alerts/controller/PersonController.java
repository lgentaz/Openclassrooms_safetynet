package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public Iterable<Person> list() {
        return personService.list();
    }

    @GetMapping("/personinfo")
    public Iterable<Person> findByFullName(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        return personService.findByFullName(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public Set<String> emailListByCity(@RequestParam(value = "city") String city) {
        return personService.findEmailByCity(city);
    }
}
