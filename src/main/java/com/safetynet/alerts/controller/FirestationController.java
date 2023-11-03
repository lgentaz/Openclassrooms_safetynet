package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FirestationService;
import org.pmw.tinylog.Logger;
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

    @PostMapping("/firestation")
    public void createPerson(String address, String station){
        firestationService.createFirestation(address, station);
    }

    @PutMapping("/firestation")
    public void updatePerson(String address, String station){
    //    firestationService.createFirestation(address, station);
    }

    @DeleteMapping("/firestation/{stationNumber}")
    public Map<String, Boolean> deleteFirestationsByStation(@PathVariable("stationNumber") String stationNumber) throws ResourceNotFoundException {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.FALSE);
        try {
            Logger.info(firestationService.findFirestationsbyStation(stationNumber));
            firestationService.deleteFirestationsByStation(stationNumber);
            Logger.info("Firestation n°" + stationNumber + " deleted.");
            response.put("deleted", Boolean.TRUE);
        }
        catch(ResourceNotFoundException e) {
            Logger.info("No firestation found for this station number : " + stationNumber);
        }
        Logger.info("Response: " + response.toString());
        return response;
    }

    @DeleteMapping("/firestation/d/{address}")
    public Map<String, Boolean> deleteFirestationsByAddress(@PathVariable("address") String address) throws ResourceNotFoundException {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.FALSE);
        try {
            //firestationService.findFirestationsbyStation(stationNumber).iterator().hasNext();
            firestationService.deleteFirestationsByAddress(address);
            Logger.info("Firestation at " + address + " was deleted.");
            response.put("deleted", Boolean.TRUE);
    }
        catch(ResourceNotFoundException e) {
        Logger.info("No firestation found for this address : " + address);
    }
        Logger.info("Response: " + response.toString());
        return response;
    }


}
