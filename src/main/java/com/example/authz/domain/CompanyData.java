package com.example.authz.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CompanyData {
    private final String name;
    private final String country;

    @JsonCreator
    public CompanyData(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "CompanyData{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
