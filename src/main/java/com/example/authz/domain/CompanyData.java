package com.example.authz.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CompanyData {
    public String name;
    public String country;

    @JsonCreator
    public CompanyData(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return "CompanyData{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
