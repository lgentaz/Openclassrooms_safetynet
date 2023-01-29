package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Configuration
@ComponentScan("com.safetynet.alerts.repository")
public class MedicalRecordService {
    @Autowired
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public Iterable<MedicalRecord> list() {
        return medicalRecordRepository.findAll();
    }

    public List<MedicalRecord> findMedicalRecordByFullName(String firstName, String lastName) {
        List<MedicalRecord> allFirstName = medicalRecordRepository.findAll().stream().filter(medicalRecord -> medicalRecord.getFirstName().contentEquals(firstName)).collect(Collectors.toList());
        List<MedicalRecord> allFullName = allFirstName.stream().filter(medicalRecord -> medicalRecord.getLastName().contentEquals(lastName)).collect(Collectors.toList());
        return allFullName;
    }
}
