package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Configuration
@ComponentScan("com.safetynet.alerts.repository")
public class PersonService {
    @Autowired private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> list() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public List<Person> findPersonByFullName(String firstName, String lastName) {
        List<Person> allFullName = this.list().stream().filter(person -> person.getLastName().contentEquals(lastName) && person.getFirstName().contentEquals(firstName)).collect(Collectors.toList());
        return allFullName;
    }

    public List<Person> findPersonByAddress(String address) {
        List<Person> allAddress = this.list().stream().filter(person -> person.getAddress().equals(address)).collect(Collectors.toList());
        return allAddress;
    }

    public Set<String> findEmailByCity(String city) {
        return personRepository.findAll().stream().filter(person -> person.getCity().contentEquals(city)).map(Person::getEmail).collect(Collectors.toSet());
    }

    public void deletePerson(String firstName, String lastName) {
        this.findPersonByFullName(firstName,lastName).forEach(person -> personRepository.delete(person));
    }

    public void createPerson(String firstName, String lastName, String phone, String email, String address, String city, String zip){
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phone);
        person.setEmail(email);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        personRepository.save(person);
    }

    public void updatePerson(){}
}
