package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FirestationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class FirestationController {
    private final FirestationService firestationService;

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping("/firestations")
    public Iterable<Firestation> list() {
        return firestationService.list();
    }

    @GetMapping("/firestation")
    public Iterable<Person> findByStation(@RequestParam(value = "stationNumber") String stationNumber)  {
        return firestationService.findByStation(stationNumber);
    }

    @GetMapping("/phoneAlert")
    public Iterable<String> findPhoneNumbers(@RequestParam(value = "firestation") String firestation_number)  {
        return firestationService.findPhoneNumbers(firestation_number);
    }

}
