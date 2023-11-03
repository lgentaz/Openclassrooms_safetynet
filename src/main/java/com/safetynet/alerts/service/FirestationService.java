package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Configuration
@ComponentScan("com.safetynet.alerts.repository")
public class FirestationService {
    @Autowired
    private final FirestationRepository firestationRepository;

    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }

    public List<Firestation> list() {
        return firestationRepository.findAll();
    }

    public List<Firestation> findFirestationsbyStation(String stationNumber) {
        return this.list().stream().filter(firestation -> firestation.getStation().contentEquals(stationNumber)).collect(Collectors.toList());
    }

    public List<Firestation> findFirestationsbyAddress(String address) {
        return this.list().stream().filter(firestation -> firestation.getAddress().contentEquals(address)).collect(Collectors.toList());
    }

    public List<String> getFirestationAddresses(String stationNumber){
        return this.findFirestationsbyStation(stationNumber).stream().map(Firestation::getAddress).collect(Collectors.toList());
    }

    public void deleteFirestationsByStation(String stationNumber) {
        Iterable<Firestation> selectedFirestations = this.findFirestationsbyStation(stationNumber);
        selectedFirestations.forEach(firestation -> firestationRepository.delete(firestation));
    }

    public void deleteFirestationsByAddress(String address) {
        Iterable<Firestation> selectedFirestations = this.findFirestationsbyAddress(address);
        selectedFirestations.forEach(firestation -> firestationRepository.delete(firestation));
    }

    public void createFirestation(String address, String station) {
        Firestation firestation = new Firestation(address, station);
        firestationRepository.save(firestation);
    }
}
