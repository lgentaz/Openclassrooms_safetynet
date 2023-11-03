package com.safetynet.alerts.model;

import java.util.List;

public class House {
    String address;
    List<HouseholdMember> inhabitants;

    public House(String address, List<HouseholdMember> inhabitants) {
        this.address = address;
        this.inhabitants = inhabitants;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<HouseholdMember> getInhabitants() {
        return inhabitants;
    }

    public void setHouseholdList(List<HouseholdMember> inhabitants) {
        this.inhabitants = inhabitants;
    }

}
