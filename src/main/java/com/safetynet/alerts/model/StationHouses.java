package com.safetynet.alerts.model;

import java.util.List;

public class StationHouses {
    String stationNumber;
    List<House> houses;

    public StationHouses(String stationNumber, List<House> houses) {
        this.stationNumber = stationNumber;
        this.houses = houses;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
