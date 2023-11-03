package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    public List<MedicalRecord> list() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord findMedicalRecordByFullName(String firstName, String lastName) {
        MedicalRecord fullNameRecord = this.list().stream().filter(medicalRecord -> medicalRecord.getFirstName().contentEquals(firstName) && medicalRecord.getLastName().contentEquals(lastName)).findFirst().get();
        return fullNameRecord;
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.delete(this.findMedicalRecordByFullName(firstName,lastName));
    }

    public void createMedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);
    }

    public Long findAge(MedicalRecord medicalRecord) {
        LocalDate birthdate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
        return (long) LocalDate.now().compareTo(birthdate);
    }
}
