package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
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

    public Iterable<Person> list() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public List<Person> findByFullName(String firstName, String lastName) {
        List<Person> allFirstName = personRepository.findAll().stream().filter(person -> person.getFirstName().contentEquals(firstName)).collect(Collectors.toList());
        List<Person> allFullName = allFirstName.stream().filter(person -> person.getLastName().contentEquals(lastName)).collect(Collectors.toList());
        return allFullName;
    }

    public Set<String> findEmailByCity(String city) {
        return personRepository.findAll().stream().filter(person -> person.getCity().contentEquals(city)).map(Person::getEmail).collect(Collectors.toSet());
    }
}
