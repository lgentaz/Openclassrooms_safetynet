package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FirestationService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @DeleteMapping("/firestation/delete")
    public Map<String, Boolean> deleteFirestationsByStation(@RequestParam(value = "firestation") String stationNumber) throws ResourceNotFoundException {
//        firestationService.findStation(stationNumber).getStation().orElseThrow(() -> new ResourceNotFoundException("Firestation not found for this number : " + stationNumber));
        firestationService.deleteFirestationsByStation(stationNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/firestation/delete")
    public Map<String, Boolean> deleteFirestationsByAddress(@RequestParam(value = "address") String address) throws ResourceNotFoundException {
//        firestationService.findStation(stationNumber).getStation().orElseThrow(() -> new ResourceNotFoundException("Firestation not found for this address : " + address));
        firestationService.deleteFirestationsByAddress(address);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/phoneAlert")
    public Iterable<String> findPhoneNumbers(@RequestParam(value = "firestation") String stationNumber)  {
        return firestationService.findPhoneNumbers(stationNumber);
    }

}
