package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class JsonDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Person> persons;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Firestation> firestations;
    @OneToMany(cascade=CascadeType.ALL)
    private List<MedicalRecord> medicalrecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonDB() {
    }
}
