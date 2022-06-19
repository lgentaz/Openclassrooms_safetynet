package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="persons")
public class Person {
    public Person() {
    }
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String zip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
