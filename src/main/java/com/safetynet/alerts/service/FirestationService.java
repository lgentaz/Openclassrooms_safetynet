package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
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
    @Autowired
    private final PersonRepository personRepository;

    public FirestationService(FirestationRepository firestationRepository,PersonRepository personRepository) {
        this.firestationRepository = firestationRepository;
        this.personRepository = personRepository;
    }

    public Iterable<Firestation> list() {
        return firestationRepository.findAll();
    }

    public Iterable<Person> findByStation(String stationNumber) {
        List<String> stationAddresses = firestationRepository.findAll().stream().filter(firestation -> firestation.getStation().contentEquals(stationNumber)).map(Firestation::getAddress).collect(Collectors.toList());
        List<Person> localPersons = new ArrayList<>();
        for (String address :stationAddresses) {
            localPersons.addAll(personRepository.findAll().stream().filter(person -> person.getAddress().equals(address)).collect(Collectors.toList()));
        };
        return localPersons;
    }

    public Iterable<String> findPhoneNumbers(String firestation_number) {
        Iterable<Person> alertedPeople = this.findByStation(firestation_number);
        Set<String> phoneList = new HashSet<>();
        alertedPeople.forEach(person -> phoneList.add(person.getPhone()));
        return phoneList;
    }

    public void deleteFirestationsByStation(String stationNumber) {
        List<Firestation> selectedFirestations = this.findFirestationsbyStation(stationNumber);
        selectedFirestations.forEach(firestation -> firestationRepository.delete(firestation));
    }

    public void deleteFirestationsByAddress(String address) {
        List<Firestation> selectedFirestations = this.findFirestationsbyAddress(address);
    }

    public List<Firestation> findFirestationsbyStation(String stationNumber) {
        return firestationRepository.findAll().stream().filter(firestation -> firestation.getStation().contentEquals(stationNumber)).collect(Collectors.toList());
    }

    public List<Firestation> findFirestationsbyAddress(String address) {
        return firestationRepository.findAll().stream().filter(firestation -> firestation.getAddress().contentEquals(address)).collect(Collectors.toList());
    }
}
