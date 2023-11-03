package com.safetynet.alerts.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="medicalRecords")
public class MedicalRecord {
    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String firstName;
    private String lastName;
    private String birthdate;
    @ElementCollection
    private List<String> medications;
    @ElementCollection
    private List<String> allergies;

    public MedicalRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
