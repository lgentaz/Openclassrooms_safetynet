package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="firestations")
public class Firestation {
    public Firestation(String address, String station) {
        this.address = address;
        this.station = station;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String address;

    public String getAddress() {
        return address;
    }

    public String getStation() {
        return station;
    }

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
