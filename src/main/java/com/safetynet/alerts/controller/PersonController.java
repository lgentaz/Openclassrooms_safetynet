package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class PersonController {
    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public Iterable<Person> list() {
        return personService.list();
    }

//    @PostMapping("/person")
//    public void createPerson(){
//
//    }

    @DeleteMapping("/person/delete")
    public Map<String, Boolean> deletePerson(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName")String lastName)throws ResourceNotFoundException {
        personService.findPersonByFullName(firstName, lastName).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("Person not found for this name : " + firstName + " " + lastName));
        personService.deletePerson(firstName, lastName);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/personinfo")
    public Iterable<Person> findByFullName(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        return personService.findPersonByFullName(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public Set<String> emailListByCity(@RequestParam(value = "city") String city) {
        return personService.findEmailByCity(city);
    }
}
