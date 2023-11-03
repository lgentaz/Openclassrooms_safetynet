package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.pmw.tinylog.Logger;
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

    @PostMapping("/person")
    public void createPerson(String firstName, String lastName, String phone, String email, String address, String city, String zip){
        personService.createPerson(firstName,lastName,phone,email,address,city,zip);
    }

    @PutMapping("/person")
    public void updatePerson(String firstName, String lastName, String phone, String email, String address, String city, String zip){
    //    personService.createPerson(firstName,lastName,phone,email,address,city,zip);
    }

    @DeleteMapping("/person")
    public Map<String, Boolean> deletePerson(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName")String lastName)throws ResourceNotFoundException {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.FALSE);
        try {
            personService.deletePerson(firstName, lastName);
            Logger.info(firstName + " " + lastName + " was deleted.");
            response.put("deleted", Boolean.TRUE);
        }
        catch(ResourceNotFoundException e) {
            Logger.info("Person not found for this name : " + firstName + " " + lastName);
        }
        Logger.info("Response: " + response.toString());
        return response;

    }
}
