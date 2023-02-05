package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @DeleteMapping("/medicalRecord/delete")
    public Map<String, Boolean> deletePerson(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName")String lastName)throws ResourceNotFoundException {
        medicalRecordService.findMedicalRecordByFullName(firstName, lastName).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("MedicalRecord not found for this name : " + firstName + " " + lastName));
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
