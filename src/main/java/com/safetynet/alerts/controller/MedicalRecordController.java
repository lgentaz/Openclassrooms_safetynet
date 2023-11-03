package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.pmw.tinylog.Logger;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/medicalRecords")
    public Iterable<MedicalRecord> list() {
        return medicalRecordService.list();
    }

    @PostMapping("/medicalRecord")
    public void createRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        medicalRecordService.createMedicalRecord(firstName, lastName, birthdate, medications, allergies);
    }

    @PutMapping("/medicalRecord")
    public void updateRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        //medicalRecordService.createMedicalRecord(firstName, lastName, birthdate, medications, allergies);
    }

    @DeleteMapping("/medicalRecord")
    public Map<String, Boolean> deleteRecord(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName")String lastName)throws ResourceNotFoundException {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.FALSE);
        try {
            Logger.info(medicalRecordService.findMedicalRecordByFullName(firstName, lastName));
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
            Logger.info("deleted.");
            response.put("deleted", Boolean.TRUE);
        }
        catch(ResourceNotFoundException e) {
            Logger.info("No medical record found for : " + firstName + " " + lastName);
        }
        Logger.info("Response: " + response.toString());
        return response;
    }

}
