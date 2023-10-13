package com.example.authz.domain;

public class Company extends CompanyData {
    private final int id;

    public Company(int id, CompanyData data) {
        super(data.getName(), data.getCountry());
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
