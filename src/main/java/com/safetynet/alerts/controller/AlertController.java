package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Child;
import com.safetynet.alerts.model.HouseholdMember;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonInfo;
import com.safetynet.alerts.service.AlertService;
import com.safetynet.alerts.service.FirestationService;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class AlertController {
    private final FirestationService firestationService;
    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AlertService alertService;

    public AlertController(FirestationService firestationService, PersonService personService, MedicalRecordService medicalRecordService,AlertService alertService) {
        this.firestationService = firestationService;
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.alertService = alertService;
    }


    @GetMapping("/firestation")
    public List<Map<String,Object>> findByStation(@RequestParam(value = "stationNumber") String stationNumber)  {
        List<Map<String,Object>> response = new ArrayList<>();
        Map<String,Object> persons = new HashMap<>();
        persons.put("persons", alertService.findShortPersonInfoByStation(stationNumber));
        persons.put("ageGroups", alertService.findAgeGroupsByStation(stationNumber));
        response.add(persons);
        return response;
    }

    @GetMapping("/childAlert")
    public List<Map<String,Object>> findChild(@RequestParam(value = "address") String address)  {
        List<Map<String,Object>> response = new ArrayList<>();
        if (!alertService.findChildInfoByAddress(address).isEmpty()){
            Map<String,Object> childInfo = new HashMap<>();
            childInfo.put("children", alertService.findChildInfoByAddress(address));
            childInfo.put("adults",alertService.findAdultsNamesByAddress(address));
            response.add(childInfo);
        }
        return response;
    }

    @GetMapping("/phoneAlert")
    public Iterable<String> findPhoneNumbers(@RequestParam(value = "firestation") String stationNumber)  {
        return alertService.findPhoneNumbers(stationNumber);
    }

    @GetMapping("/fire")
    public List<Map<String,Object>> rescueInfo(@RequestParam(value = "address") String address)  {
        List<Map<String,Object>> response = new ArrayList<>();
        Map<String,Object> station = new HashMap<>();
        station.put("firestation number", firestationService.findFirestationsbyAddress(address).get(0).getStation());
        station.put("household inhabitants", alertService.findHouseholdMembersByAddress(address));
        response.add(station);
        return response;
    }

    @GetMapping("/stations")
    public Map<String,Object> neighbourhoodStations(@RequestParam(value = "stations") List<String> stations)  {
        Map<String,Object> response = new HashMap<>();
        response.put("stations",alertService.findStationsInfo(stations));
        return response;
    }

    @GetMapping("/personInfo")
    public List<PersonInfo> findByFullName(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        return alertService.findPersonInfoByFullName(firstName,lastName);
    }

    @GetMapping("/communityEmail") //OK
    public Set<String> emailListByCity(@RequestParam(value = "city") String city) {
        return personService.findEmailByCity(city);
    }
}
