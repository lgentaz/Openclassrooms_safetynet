package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="firestationss")
public class Firestation {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String address;
    private String station;
    public Firestation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
