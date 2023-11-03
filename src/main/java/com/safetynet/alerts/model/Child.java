package com.safetynet.alerts.model;

public class Child {

    //Pour chaque enfant
    //prénom
    // nom de famille
    // âge
    // liste des autres membres du foyer.
    private String firstName;
    private String lastName;
    private Long age;

    public Child(String firstName, String lastName, Long age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
