package com.safetynet.alerts.model;

import java.util.List;

public class HouseholdMember {
    // utiliser pour [/stations?stations=<a_list_of_station_numbers>] et [/fire?address=<address_>]
    // pour chaque foyer/adresse.
    //nom
    //téléphone
    //âge
    //antécédents médicaux (médicaments, posologie et allergies)

    public HouseholdMember(String firstName, String lastName, String phone, Long age, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    private String firstName;
    private String lastName;
    private String phone;
    private Long age;
    private List<String> medications;
    private List<String> allergies;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
