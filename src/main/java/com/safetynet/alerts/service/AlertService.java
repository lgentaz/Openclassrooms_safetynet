package com.safetynet.alerts.service;

import com.safetynet.alerts.model.*;
import com.safetynet.alerts.model.StationHouses;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Configuration
@ComponentScan("com.safetynet.alerts.repository")
public class AlertService {
    private final FirestationService firestationService;
    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;

    public AlertService(FirestationService firestationService, PersonService personService, MedicalRecordService medicalRecordService) {
        this.firestationService = firestationService;
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    public Iterable<Person> findPersonsByStation(String stationNumber) {
        List<String> stationAddresses = firestationService.getFirestationAddresses(stationNumber);
        List<Person> localPersons = new ArrayList<>();
        for (String address :stationAddresses) {
            localPersons.addAll(personService.findPersonByAddress(address));
        }
        return localPersons;
    }

    public ShortPersonInfo findEssentialInfo(Person person){
        ShortPersonInfo shortInfo = new ShortPersonInfo(person.getFirstName(),person.getLastName(), person.getAddress(), person.getPhone());
        return shortInfo;
    }

    public List<ShortPersonInfo> findShortPersonInfoByStation(String stationNumber) {
        Iterable<Person> persons = this.findPersonsByStation(stationNumber);
        List<ShortPersonInfo> shortPersons = new ArrayList<>();
        persons.forEach(person -> shortPersons.add(this.findEssentialInfo(person)));
        return shortPersons;
    }

    public Iterable<String> findPhoneNumbers(String firestation_number) {
        Iterable<Person> alertedPeople = this.findPersonsByStation(firestation_number);
        Set<String> phoneList = new HashSet<>();
        alertedPeople.forEach(person -> phoneList.add(person.getPhone()));
        return phoneList;
    }

    public Map<String,Long> findAgeGroupsByStation(String stationNumber) {
        return this.findAgeGroupsByHouseholdMembers(this.findHouseholdMembersByStation(stationNumber));
    }

    public Map<String,Long> findAgeGroupsByHouseholdMembers(List<HouseholdMember> members){
        List<Long> localAges = members.stream().map(member->member.getAge()).collect(Collectors.toList());
        Map<String,Long> ageGroups = new HashMap<>();
        ageGroups.put("number of adults", localAges.stream().filter(age -> age>=18).count());
        ageGroups.put("number of children", localAges.stream().filter(age -> age<18).count());
        return ageGroups;
    }

    public List<HouseholdMember> findHouseholdMembersByAddress(String address){
        List<HouseholdMember> householdMembers = new ArrayList<>();
        personService.findPersonByAddress(address).forEach(person -> householdMembers.add(this.createHouseholdMember(person)));
        return householdMembers;
    }

    public List<HouseholdMember> findHouseholdMembersByStation(String stationNumber){
        List addresses = firestationService.getFirestationAddresses(stationNumber);
        List<HouseholdMember> membersByStation = new ArrayList<>();
        for (Object address : addresses) {
            membersByStation.addAll(this.findHouseholdMembersByAddress(address.toString()));
        }
        return membersByStation;
    }

    public HouseholdMember createHouseholdMember(Person person){
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFullName(person.getFirstName(),person.getLastName());
        Long age = medicalRecordService.findAge(medicalRecord);
        return new HouseholdMember(person.getFirstName(), person.getLastName(), person.getPhone(),age,medicalRecord.getMedications(), medicalRecord.getAllergies());
    }
    public PersonInfo createPersonInfo(Person person){
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFullName(person.getFirstName(),person.getLastName());
        Long age = medicalRecordService.findAge(medicalRecord);
        return new PersonInfo(person.getFirstName(), person.getLastName(), person.getAddress(), age, person.getEmail(), medicalRecord.getMedications(), medicalRecord.getAllergies());
    }

    public List<Child> findChildInfoByAddress(String address){
        List<HouseholdMember> householdMembers = this.findHouseholdMembersByAddress(address);
        List<HouseholdMember> children = householdMembers.stream().filter(member->member.getAge()<18).collect(Collectors.toList());;
        List<Child> childInfo = new ArrayList<>();
        children.forEach(child->childInfo.add(new Child(child.getFirstName(),child.getLastName(),child.getAge())));
        return childInfo;
    }
    public List<FullName> findAdultsNamesByAddress(String address){
        List<HouseholdMember> householdMembers = this.findHouseholdMembersByAddress(address);
        List<FullName> adults = householdMembers.stream().filter(member->member.getAge()>=18).map(member->this.mapHouseholdToFullName(member)).collect(Collectors.toList());
        return adults;
    }

    public FullName mapHouseholdToFullName(HouseholdMember member){
        return new FullName(member.getFirstName(),member.getLastName());
    }

    public List<StationHouses> findStationsInfo(List<String> stations) {
        List<StationHouses> stationHouses = new ArrayList<>();
        for (String stationNumber : stations) {
            List<House> houses = new ArrayList<>();
            firestationService.getFirestationAddresses(stationNumber).stream().forEach(address-> houses.add(new House(address, findHouseholdMembersByAddress(address))));
            stationHouses.add(new StationHouses(stationNumber,houses));
        }
        return stationHouses;
    }

    public List<PersonInfo> findPersonInfoByFullName(String firstName, String lastName) {
        List<PersonInfo> personsInfo = new ArrayList<>();
        List<Person> allFullName = personService.list().stream().filter(person -> person.getLastName().contentEquals(lastName) && person.getFirstName().contentEquals(firstName)).collect(Collectors.toList());
        allFullName.forEach(person -> personsInfo.add(this.createPersonInfo(person)));
        return personsInfo;
    }

}
