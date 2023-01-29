package com.safetynet.alerts.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="medicalRecords")
public class MedicalRecord {
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
